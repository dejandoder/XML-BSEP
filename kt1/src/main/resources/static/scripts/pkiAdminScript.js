$(document).ready(function($) {


	//OTVARANJE TABOVA
	$(".content-link").click(function(event) {
		/* Act on the event */
		event.preventDefault();

		$(".content-link").each(function(index, el) {
			$(this).removeClass('active');
		});
	
		$(".content-tab").each(function(index, el) {
			$(this).hide();
		});

		$(this).addClass('active');
		$("#".concat($(this).attr("id").slice(0,-7).concat("Tab"))).show();
	});
	
	$("#newCertificateNavLink").trigger('click');

	 $("#fromDatePicker").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-right",
        minView:2,
    });

    $("#toDatePicker").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-right",
        minView:2,
    });
});