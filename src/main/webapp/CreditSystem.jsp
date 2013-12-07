<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/> 
</head>
<body >
<br />
<br />

<legend>Credit Management System</legend>
<form action="/credit/updateUserCredit" method="POST">
<div class = "container">
<div class="row">
  <div class="span4">
  <h4>Enter user : </h4>
  </div>
  
  <div class="span7">
  <input type="text" name="user" placeholder="Enter email" class="input-large" id="focusedInput" style="font-size:12pt;height:30px">
  </div>
</div>
<br />
<div class="row">
  <div class="span4">
 <h4> Enter credits to update :</h4>
  </div>
  
  <div class="span7">
 <input type="number" name="credit" style="font-size:12pt;height:30px"/>
  </div>
</div>
<br />
<div class="row">
  <div class="span4">
<h4>Reason for credits :</h4>
  </div>
  
  <div class="span7">
<select>
  <option value="ClassParticipation">Class Participation</option>
  <option value="Platform">Sensor Platform Contribution</option>
  <option value="Social Service">Social Service</option>
  <option value="AGrader">A Grader</option>
  <option value="Innovation">Innovation</option>
  <option value="VIP">Value in Performance</option>  
</select>
  </div>
</div>
<br />
<br />
<div class="row">
<input type="submit" value="Give Credits" class="btn btn-primary" />

  </div>

</div>




    </div>
  </div>
</form>



</body>
</html>

