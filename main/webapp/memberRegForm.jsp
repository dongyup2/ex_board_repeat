<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member RegForm</title>
</head>
<body>
<h1>Member RegForm</h1>
<hr>
<form name="frm_reg" action="memberRegist" method="POST">
	<input type="text" name="name" placeholder="Input name.."><br>
	<input type="text" name="id" placeholder="Input id..">
	<input type="button" id="btn_id" data-init="0" value="id중복확인" disabled><br>
	<div id="result"></div>
	<input type="password" name="pw" placeholder="Input pw.."><br>
	<input type="password" name="repw" placeholder="Input repw.."><br>
	<input type="text" name="tel" placeholder="Input tel.."><br>
	<input type="text" name="addr" placeholder="Input addr.."><br>
	<input type="submit" value="등록" onclick="return inputCheck()">&nbsp;&nbsp;
</form>
<script>
	const txt_id = document.querySelector("input[name='id']");
	const btn_idcheck = document.querySelector("#btn_id");
	const div_result = document.getElementById("result");
	btn_idcheck.addEventListener('click', idCheck);
	txt_id.addEventListener('keyup', checkReset);
		
	const txt_pw = document.querySelector("input[name='pw']");
	const txt_repw = document.querySelector("input[name='repw']");
	txt_repw.addEventListener('change', checkSame);
	
	function checkSame(){
		console.log(txt_pw.value);
		console.log(txt_repw.value);
		if(txt_repw.value != txt_pw.value ){
			alert("패스워드와 일치하지 않습니다. 다시 입력하세요.");
			txt_repw.value="";
			txt_repw.focus();
			return false;
		}else if(txt_repw.value.length == 0 && txt_pw.value.length == 0){
			alert("패스워드를 입력하지 않았습니다. 확인하세요.");
			txt_pw.focus();
			return false;
		}
		
		
		return true;
	}
	
	function checkReset(){
		btn_idcheck.disabled = false;
		div_result.innerHTML = "아이디 중복확인을 해야합니다.";
	}
	function idCheck(){
		btn_idcheck.dataset.init = 1;
		//id 중복체크
		//id 텍스트 박스의 value값을 서버에 보내기.
		//1. 객체생성
		const xhr = new XMLHttpRequest();
		//2. 응답처리함수
		xhr.onload = function(){
			let checkResult = this.responseText; // 0 아니면 1
			if(checkResult === "1"){
				div_result.innerHTML = "<span style='color: red;'>이미 사용중인 아이디입니다.</span>";
				btn_idcheck.disabled = false;
			}else{
				div_result.innerHTML = "<span style='color: blue;'>사용 가능한 아이디입니다.</span>";
				btn_idcheck.disabled = true;
			}
		}
		//3. 서버경로설정
		xhr.open("POST", "idCheck", true);
		//4. 서버에 전송(POST 방식일 경우 데이터 포함)
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send("id=" + txt_id.value);
		
		
		//서버에서 온 응답 데이터(1 또는 0)를 통해 사용자에게 id 사용가능여부를 알려주기
		// 1: 이미 사용중입니다. 0: 사용가능합니다.
		
	}
	
	function inputCheck(){
		//form 데이터 입력 체크
		if(frm_reg.name.value.length == 0){
			alert("이름을 입력하세요.");
			frm_reg.name.focus();
			return false;
		}
		if(frm_reg.id.value.length == 0){
			alert("id를 입력하세요.");
			frm_reg.id.focus();
			return false;
		}
		if(frm_reg.pw.value.length == 0){
			alert("pw를 입력하세요.");
			frm_reg.pw.focus();
			return false;
		}
		if(frm_reg.repw.value.length == 0){
			alert("repw를 입력하세요.");
			frm_reg.repw.focus();
			return false;
		}
		if(frm_reg.tel.value.length == 0){
			alert("pw를 입력하세요.");
			frm_reg.tel.focus();
			return false;
		}
		if(btn_idcheck.disabled == false || btn_idcheck.getAttribute("data-init") == 0){
			alert("아이디 중복확인을 하세요...");
			btn_idcheck.focus();
			return false;
		}
		if(!checkSame()){
			
			return false;
		}
		
		return true;
	}
</script>

</body>
</html>