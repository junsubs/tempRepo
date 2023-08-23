//상세조회 누르면 모달
document.getElementById("moreList").onclick = function() {
    document.getElementById("reportMore").style.display="block";
    document.body.classList.add("stop-scroll");
}

document.getElementById("close").onclick = function() {
    document.getElementById("reportMore").style.display="none";
    document.body.classList.remove("stop-scroll");
} 
