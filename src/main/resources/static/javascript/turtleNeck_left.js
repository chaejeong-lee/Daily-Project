const video = document.getElementById("video");
const canvas = document.getElementById("canvas");
const context = canvas.getContext("2d");

const result_label = document.getElementById("result_label");
let pose_status = 2;
let keep_time = [0, 0, 0];
let result_message = "";

//webcam을 enable하는 코드
navigator.mediaDevices.getUserMedia({video: true, audio: false}).then(function (stream) {
    video.srcObject = stream;
});

//then 안쪽이 function(model){} 이렇게 쓰는거랑 같다 (인자가 하나라 중괄호가 없는 것)
posenet.load().then((model) => {
    // 이곳의 model과 아래 predict의 model은 같아야 한다.
    video.onloadeddata = (e) => {
        //비디오가 load된 다음에 predict하도록. (안하면 콘솔에 에러뜸)
        predict();
    };

    function predict() {
        //frame이 들어올 때마다 estimate를 해야하니 함수화 시킴
        model.estimateSinglePose(video).then((pose) => {
            canvas.width = video.width; //캔버스와 비디오의 크기를 일치시킴
            canvas.height = video.height;

            //spineTop
            leftShoulder = pose.keypoints[5].position;
            rightShoulder = pose.keypoints[6].position;

            spineTopX = (leftShoulder.x + rightShoulder.x) / 2;
            spineTopY = (leftShoulder.y + rightShoulder.y) / 2;
            scale = 1;

            //spineBottom
            leftHip = pose.keypoints[11].position;
            rightHip = pose.keypoints[12].position;

            spineBottomX = (leftHip.x + rightHip.x) / 2;
            spineBottomY = (leftHip.y + rightHip.y) / 2;

            //spineMiddle
            spineMiddleX = (spineBottomX + spineTopX) / 2;
            spineMiddleY = (spineBottomY + spineTopY) / 2;

            spine = [spineTopX, spineTopY, spineBottomX, spineBottomY, spineMiddleX, spineMiddleY];

            //spineTop
            drawPoint(context, spineTopY, spineTopX, 3, color); //////////                      keypoint!!!!!!!!!!!!!!!!!!!
            drawPoint(context, spineBottomY, spineBottomX, 3, color);
            drawPoint(context, spineMiddleY, spineMiddleX, 3, color);

            drawKeypoints(pose.keypoints, 0.6, context);
            drawSkeleton(pose.keypoints, 0.6, context);

            checkPose(pose, spine);
        });
        requestAnimationFrame(predict); //frame이 들어올 때마다 재귀호출
    }
});

/* Timer */
let count_time = setInterval(function () {
    if (keep_time[pose_status] == 0) {
        //다른 모션에서 바뀌어 들어옴
        keep_time[0] = keep_time[1] = keep_time[2] = 0;
        keep_time[pose_status]++;
    } else {
        if (pose_status != 2 && keep_time[pose_status] == 10) {
            if (pose_status == 0) {//목의 차이가 2.5cm이상일 경우
                result_message = "목이 조금 내려왔습니다. 자세를 교정하여 앉아 주세요.";
                keep_time[0] = keep_time[1] = keep_time[2] = 0;
            } else {
                result_message = "자세가 너무 좋지 않습니다. 바로 앉아주세요.";
                keep_time[0] = keep_time[1] = keep_time[2] = 0;
            }
            speech(result_message);
            window.parent.postMessage(result_message, "*");
            let interval = setInterval(count_time, 3000);
        } else if (pose_status == 2 && keep_time[pose_status] == 10) {
            result_message = "정상";
            keep_time[0] = keep_time[1] = keep_time[2] = 0;
            window.parent.postMessage(result_message, "*");
            let interval = setInterval(count_time, 3000);
        }
        keep_time[pose_status]++; //시간은 항상 세고 있다.
    }
}, 1000);

function checkPose(pose, spine) {
    if(checkNeck25(pose)) {
        pose_status = 0;
    } else if (checkNeck50(pose)) {
        pose_status = 1;
    } else {
        pose_status = 2;
    }
    console.log(pose_status);
}

function checkNeck25(pose) {
    leftShoulder = pose.keypoints[5].position;
    leftEar = pose.keypoints[3].position;

    return Math.abs(leftEar.x - leftShoulder.x) > 25 && Math.abs(leftEar.x - leftShoulder.x) < 50;
}

function checkNeck50(pose) {
    leftShoulder = pose.keypoints[5].position;
    leftEar = pose.keypoints[3].position;

    return Math.abs(leftEar.x - leftShoulder.x) >= 50;
}

/* PoseNet을 쓰면서 사용하는 함수들 코드 - 그냥 복사해서 쓰기 */
//tensorflow에서 제공하는 js 파트
const color = "aqua";
const boundingBoxColor = "red";
const lineWidth = 2;
function toTuple({y, x}) {
    return [y, x];
}

function drawPoint(ctx, y, x, r, color) {
    ctx.beginPath();
    ctx.arc(x, y, r, 0, 2 * Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
}

function drawSegment([ay, ax], [by, bx], color, scale, ctx) {
    ctx.beginPath();
    ctx.moveTo(ax * scale, ay * scale);
    ctx.lineTo(bx * scale, by * scale);
    ctx.lineWidth = lineWidth;
    ctx.strokeStyle = color;
    ctx.stroke();
}

function drawSkeleton(keypoints, minConfidence, ctx, scale = 1) {
    const adjacentKeyPoints = posenet.getAdjacentKeyPoints(keypoints, minConfidence);
    adjacentKeyPoints.forEach((keypoints) => {
        drawSegment(toTuple(keypoints[0].position), toTuple(keypoints[1].position), color, scale, ctx);
    });
}

function drawKeypoints(keypoints, minConfidence, ctx, scale = 1) {

    for (let i = 0; i < keypoints.length; i++) {
        const keypoint = keypoints[i];
        if (keypoint.score < minConfidence) {
            continue;
        }
        const {y, x} = keypoint.position;
        drawPoint(ctx, y * scale, x * scale, 3, color);
    }
}

function drawBoundingBox(keypoints, ctx) {
    const boundingBox = posenet.getBoundingBox(keypoints);
    ctx.rect(
        boundingBox.minX,
        boundingBox.minY,
        boundingBox.maxX - boundingBox.minX,
        boundingBox.maxY - boundingBox.minY
    );
    ctx.strokeStyle = boundingBoxColor;
    ctx.stroke();
}

//tts 음성지원
 var voices = [];

 function setVoiceList() {
     voices = window.speechSynthesis.getVoices();
 }
 setVoiceList();
 if (window.speechSynthesis.onvoiceschanged !== undefined) {
     window.speechSynthesis.onvoiceschanged = setVoiceList;
 }

 function speech(txt) {
     if (!window.speechSynthesis) {
         alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
         return;
     }
     var lang = 'ko-KR';
     var utterThis = new SpeechSynthesisUtterance(txt);
     utterThis.onend = function (event) {
         console.log('end');
     };
     utterThis.onerror = function (event) {
         console.log('error', event);
     };
     var voiceFound = false;
     for (var i = 0; i < voices.length; i++) {
         if (voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
             utterThis.voice = voices[i];
             voiceFound = true;
         }
     }
     if (!voiceFound) {
         alert('voice not found');
         return;
     }
     utterThis.lang = lang;
     utterThis.pitch = 1;
     utterThis.rate = 1; //속도
     window.speechSynthesis.speak(utterThis);
 }