// 이미지 누르면 모달
function openModal() {
    document.getElementById("accomMore").style.display="block";
    document.body.classList.add("stop-scroll");
}

// 모달 닫기
document.getElementById("close").onclick = function() {
    document.getElementById("accomMore").style.display="none";
    document.body.classList.remove("stop-scroll");
}
