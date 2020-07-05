
		<!DOCTYPE html>
		<html>
		<head>
		<title>test</title>
		<style>
		body {
			background-color: #F8F8FF;
		}
		#form-div {
			position:relative;
			margin-left:auto;
			margin-right:auto;
			background-color: #C3DFE0;
			width:300px;
			box-shadow:0 0 5px rgba(0,0,0,0.3);
		}

		#form-div-1{
		position:relative;
			margin-left:auto;
			margin-right:auto;
			background-color: #C3DFE0;
			width:300px;
			box-shadow:0 0 5px rgba(0,0,0,0.3);


		}

		.reg-button {
			background-color: #F8F8FF;
			border: 0px;
			padding:1%;
			box-shadow:0 0 5px rgba(0,0,0,0.3);
			transition:all , 0.2s;
		}


		.div-input {
			background-color: #C3DFE0;
		}

		#reg-form-name-container {

		}

		#reg-form-name {
			text-align:center;
			font-size:20px;
			font-family:Georgia;
			padding-top:2%;
			padding-bottom:1%;
		}

		.form {
			text-align:center;
		}
		.field-name{
			text-align:left;
			margin-left:20%;


		}
		.input{
			background-color:#F8F8FF;
			border:0px;
			box-shadow:0 0 1px rgba(0,0,0,0.3);
			transition: all 0.2s;
			width:90%;
			height:20px;
		}
		.input:hover {
			transform:scale(1.01);
			box-shadow:0 0 2px rgba(0,0,0,0.4);

		}

		.reg-button:hover {
			transform:scale(1.05);
			box-shadow:0 0 7px rgba(0,0,0,0.3);
		}

		.field-name{
		margin-bottom:2px;

		}

		.invalid-param {
			font-size:14px;
			margin-top: -10px;
			margin-bottom: -20px;

		}
		.invalid-message{
			font-size:14px;
			margin-left:10px;
			color: rgba(226,109,90,1);

		}
		.valid-message{
			font-size:14px;
			margin-left:10px;
			color:#3BB143;

		}

		.input:focus{
			outline:none;
			background-color: rgba(255, 255, 255,1);
			transition:background-color 0.5s;
		}
		</style>
		</head>
		<body>
		<div id="form-div">

			<h1 id="reg-form-name">Registration form</h1>
			<form class="form" method="get" novalidate>
               <div class="field-name">Login${login}</div>
               <div  class="div-input"><input class="input" type="text" name="login" size="25"></div><br/>
			   <div class="field-name">Password${password}</div>
			   <div class="div-input"><input class="input" type="password" name="password" size="25"></div><br/>
			   <div class="field-name">E-mail${email}</div>
			   <div class="div-input"><input class="input" type="email" name="email" size="25" ></div><br/>




			   <div class="div-input"><button action="login" class="reg-button"  type="submit" formmethod="GET">Registration</button></div><br/>

            </form>
		</div>
		</body>
		</html>

