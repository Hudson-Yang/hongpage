'use strict';

$(document).ready(function() {
	$(".js-btnUpdate").click(function() {
		const updatebbsno = document.querySelector(".bno").value;
		location.href = "/update.do?updatebbsno=" + updatebbsno;
	});

	$(".js-btnDelete").click(function() {
		const delbbsno = document.querySelector(".bno").value;
		if (confirm("삭제하시겠습니까?")) {
			location.href = "delete.do?delbbsno=" + delbbsno;
		} else {
			return;
		}
	});

	$(".js-btnList").click(function() {
		history.back();
	});
});
