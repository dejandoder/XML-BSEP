$(document).ready(function($) {


 	 $.ajax({
        method:'GET',
        url:'../certificate/getAll',
        contentType: 'application/json',
        success: function(data) {
            if(data){
                upisSertifikata(data);
                    }
                },
            });

 	  $.ajax({
        method:'GET',
        url:'../cl/getAllCl',
        contentType: 'application/json',
        success: function(data) {
            if(data){
                iscrtajPovezaneSert(data);
                    }
                },
            });

   pokupiCASertifikate();


   pokupiNonCASertifikate();

   function pokupiNonCASertifikate(){
      $.ajax({
        method:'GET',
        url:'../certificate/getAllNonCA',
        contentType: 'application/json',
        success: function(data) {
            if(data){
                dodajNonCASertifikate(data);
                    }
                },
            });
   };

   function pokupiCASertifikate(){
   $.ajax({
        method:'GET',
        url:'../certificate/getAllCA',
        contentType: 'application/json',
        success: function(data) {
            if(data){
                dodajCASertifikate(data);
                    }
                },
            });
 };
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
    function isEmpty(str){
        return !str.replace(/\s+/, '').length;
    }
    
    $(".provjeraPrazno").each(function(index) {
    	$(this).on('change keyup paste', function () {
    
    	if(isEmpty($(this).val()))
     	{
     		$(this).addClass("border border-danger");
     	}
    	else $(this).removeClass("border border-danger");
    })
    });
    
     $("#accountBtn").click(function(event){

     	event.preventDefault();
     	var ok=true;

     	$(".provjeraPrazno").each(function(index) {
     		if(isEmpty($(this).val()))
         	{
         		$(this).addClass("border border-danger");
         		ok=false;
         	}
     		
     	});
     	if(!ok){
     		return;
     	}
     	
     	
     	var b =$("#certificateCityInput").val();
     	var c =$("#certificateSoftwareModuleInput").val();
     	var d =$("#fromDatePicker").val();
      	var e =$("#toDatePicker").val();
      	var f =$('#caCertificateCheck').is(":checked");
      	var g =$("#signWithButton option:selected").attr("id");
      	

       
      	var sertifikat = new Object();

      	sertifikat.city=b;
      	sertifikat.softwareModule=c;
      	sertifikat.fromDate=d;
      	sertifikat.toDate=e;
      	sertifikat.ca=f;
      	

 
              $.ajax({
              	  method:'POST',
                  url:'../certificate/'+g,
                  data: JSON.stringify(sertifikat),
                  contentType: 'application/json',
                  success: function(data) {
                  	isprazniLabele();

                  	 $.ajax({
                  	  method:'GET',
                      url:'../certificate/getAll',
                      contentType: 'application/json',
                      success: function(data) {
                      if(data){
                          upisSertifikata(data);
                      }
                   },
               });

                  },
                  error: function(data) {
                  }
              });
          
      });

     function isprazniLabele(){
      pokupiNonCASertifikate();

      pokupiCASertifikate();
     	$("#certificateCountryInput").val("");

     	$("#certificateCityInput").val("");
     	$("#certificateSoftwareModuleInput").val("");
     	$("#fromDatePicker").val("");
      	$("#toDatePicker").val("");
      	$("#caCertificateCheck").val("");
      	
     };
     function dodajNonCASertifikate(data){
       $('.comunicationLink').html("");
       
       for(var i=0; i<data.length; i++){
            $('.comunicationLink').append(
                '<option id="'+data[i].serialNumber+'">'+
                    data[i].city+', '+data[i].softwareModule+
                '</option>'
              );
        }
     }
     function dodajCASertifikate(data){
    	 $('#signWithButton').html("");
        for(var i=0; i<data.length; i++){
            $('#signWithButton').append(
                '<option id="'+data[i].serialNumber+'">'+
                    data[i].city+', '+data[i].softwareModule+
                '</option>'
              );
        }
     };
      $("#addLinkBtn").click(function(event) {
      	event.preventDefault();
        var prva=$("#linkPrva option:selected").attr("id");
        var druga=$("#linkDruga option:selected").attr("id");

        $.ajax({
        method:'GET',
        url:'../cl/createLink/'+prva+'/'+druga,
        contentType: 'application/json',
        success: function(data) {
            if(data){
                iscrtajPovezaneSert(data);
                    }
                },
            });

     });

     function iscrtajPovezaneSert(data){
     	$('#tabelaPovezanihSertifikata').html("");
     	for(var i=0; i<data.length; i++){
     	$('#tabelaPovezanihSertifikata').append(
	            '<tr>'+	            
	            '<td>'+ data[i].memberOne.city+'</td>'+
	            '<td>'+ data[i].memberOne.softwareModule+'</td>'+
	            '<td></td>'+	        
	            '<td>'+ data[i].memberTwo.city +'</td>'+
	            '<td>'+ data[i].memberTwo.softwareModule +'</td>'+
	            '</tr>'
	            );
     	}
     }

     function upisSertifikata(sertifikati){

     		$('#tabelaSertifikata').html("");
	        for(var i=0; i<sertifikati.length; i++){
	        	
	          if(sertifikati[i].revoked==false){
	           $('#tabelaSertifikata').append(
	            '<tr>'+
	            '<td>'+ sertifikati[i].serialNumber +'</td>'+	            
	            '<td>'+ sertifikati[i].city+'</td>'+
	            '<td>'+ sertifikati[i].softwareModule+'</td>'+
	            '<td>'+ sertifikati[i].ca+'</td>'+
	            '<td><button  class="btn btn-outline-success my-2 my-sm-0" id="'+sertifikati[i].serialNumber+'" style="background: transparent;">Revoke</td>'+
	            '</tr>'
	            );
	       }else{

	       	$('#tabelaSertifikata').append(
	            '<tr>'+
	            '<td>'+ sertifikati[i].serialNumber +'</td>'+	            
	            '<td>'+ sertifikati[i].city+'</td>'+
	            '<td>'+ sertifikati[i].softwareModule+'</td>'+
	            '<td>'+ sertifikati[i].ca+'</td>'+
	            '<td>Revoked</td>'+
	            '</tr>'
	            );


	       }
	        }
	        
	    }

		  $("#tabelaSertifikata").on('click','button',function(event){
			  var value= $(this).text();
			  var aaa=$(this).attr('id');
			  
			  	$(this).prop("disabled",true);
			  	
			  	 $.ajax({
              	  method:'POST',
                  url:'../certificate/revokeCertificat/'+$(this).attr('id'),
                  contentType: 'application/json',
                  success: function(data) {
                         upisSertifikata(data);
                  },
              });
			  	
	 });
});