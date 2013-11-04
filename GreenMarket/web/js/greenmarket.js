
var x = 0;
var y = 0;
window.setInterval(function(){
    change_background(x);
    x++;
    x = x%2;
},5500,100);
    
window.setInterval(function(){
    change_offerte(y);
    y++;
    y = y%3;
},3500,100);   
 
 
function select_product(id){
    var product = null;
    switch(id){
        case "formaggi":
            product =  3;
            break;
        case "vini":
            product =  4;
            break;
        case "pane":
            product =  1;
            break;
        case "ortaggi":
            product =  2;
            break;
    }
    $('#id_product').val(product);
    $('#form_product').submit();
}


function change_offerte(x){    
    switch(x){
        case 0 :
            $('#container #vini').show();
            $('#container #formaggi').show();     
            $('#container #pane').hide();
            $('#container #ortaggi').hide();
            break;
        case 1 :
            $('#container #vini').hide();
            $('#container #formaggi').show();     
            $('#container #pane').show();
            $('#container #ortaggi').hide();
            break;
        case 2 :
            $('#container #vini').hide();
            $('#container #formaggi').hide();     
            $('#container #pane').show();
            $('#container #ortaggi').show();
            break;
        
    }
}
function change_background(x){    
    if(x==0){
        $('#sfondo2').fadeTo(2000, 0);
        $('#sfondo1').fadeTo(2000, 1);
    }else{
        $('#sfondo1').fadeTo(2000, 0);
        $('#sfondo2').fadeTo(2000, 1);
    }  
}

function focus_login(id){
    $("#"+id+"_lbl").hide();
}

function focusout_login(id){
    ($("#"+id).val() == "")?$("#"+id+"_lbl").show():$("#"+id+"_lbl").hide();
}

function div_login(){
    var num =  $('#show_login').val();

    if (num == 0){
        $('#show_login').val('1');

        for(i = 230 ; i>=0;i--){
            $('#all_login').css('margin-right',i+'px');
        }  
           
    }else{
        $('#show_login').val('0');
        for(i = 0 ; i<=230;i++){
            $('#all_login').css('margin-right','-'+i+'px');
        }    
           
    }
}

function submit_form(id){
    $('#submit').val(id);
    
    
    
}
function IsEmail(email) {
    var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}


function validate(){
    
    var validate = true; 
    if( $("#user").val() != "" ){
        $("#user").css("border-color","");
    }else{
        $("#user").css("border-color","red");
        validate = false; 
    }
    
    
    if( $("#psw").val()!= "" ){
        $("#psw").css("border-color","");
    }else{
        $("#psw").css("border-color","red");
        validate = false; 
    }   
    
    if( $("#address").val()!= "" ){
        $("#address").css("border-color","");
    }else{
        $("#address").css("border-color","red");
        validate = false; 
    }
    
    if( IsEmail($("#email").val()) && $("#email").val() == $("#email_conf").val()){
        $("#email").css("border-color","");
         $("#email_conf").css("border-color","");
    }else{
        $("#email").css("border-color","red");
        $("#email_conf").css("border-color","red");
        validate = false; 
    }
    

    
    return validate;
}
