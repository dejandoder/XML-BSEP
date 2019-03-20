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

     $("#accountBtn").click(function(event){

     	event.preventDefault();

     	var a =$("#certificateCountryInput").val();
     	var b =$("#certificateCityInput").val();
     	var c =$("#certificateSoftwareModuleInput").val();
     	var d =$("#fromDatePicker").val();
      	var e =$("#toDatePicker").val();
      	var f =$('#caCertificateCheck').is(":checked");
      	var g =$("#signWithButton option:selected").attr("id");
      	


      	var sertifikat = new Object();

      	sertifikat.country=a;
      	sertifikat.city=b;
      	sertifikat.softwareModule=c;
      	sertifikat.fromDate=d;
      	sertifikat.toDate=e;
      	sertifikat.ca=f;

 
              $.ajax({
              	  method:'POST',
                  url:'../certificate',
                  data: JSON.stringify(sertifikat),
                  contentType: 'application/json',
                  success: function(data) {
                  	isprazniLabele();

                  },
                  error: function(data) {
                  }
              });
          
      });

     function isprazniLabele(){

     	$("#certificateCountryInput").val("");
     	$("#certificateCityInput").val("");
     	$("#certificateSoftwareModuleInput").val("");
     	$("#fromDatePicker").val("");
      	$("#toDatePicker").val("");
      	$("#caCertificateCheck").val("");
      	
     };
});