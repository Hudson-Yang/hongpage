$(document).ready(function() {
	$('#bbsUpdate').val(bbsUpdate());
	$('#bbsDelete').val(bbsDelete());
});

function bbsUpdate() {
	var updatebbsno = document.getElementById("bno").value;
	alert("수정");
	location.href = "${path}/update.do?updatebbsno=" + updatebbsno;
}

function bbsDelete(){
	var delbbsno = document.getElementById("bno").value;
	alert("삭제");
	if(confirm("삭제하시겠습니까?")){
		location.href="delete.do?delbbsno="+delbbsno;
	}
	else{
		return;
	}
}

function bbsBack(){
	history.back();
}