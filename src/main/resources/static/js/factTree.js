
function getFactTree(factId) {
    $.ajax({
        url: "/fact/tree/"+factId,
        type: "GET",
        datatype: "json",
        async: true,
        success: function (result) {
            var status = result.status;

            if (status == 1) {
                var data = result.data;


                var canvas= document.getElementById('treeCanvas');
                var ctx = canvas.getContext("2d");



            } else {
                console.log(result.message)
            }
        }
    });

}




$(function () {
    changeCanvas();
});

window.onresize = function () {
    changeCanvas()
}

/**
 * 画板大小自适应
 */
function changeCanvas() {
    var clientHeight=document.documentElement.clientHeight;
    var clientWidth=document.documentElement.clientWidth;
    var treeCanvas= document.getElementById('treeCanvas');
    treeCanvas.width = clientWidth;
    treeCanvas.height = clientHeight;
}