# Memo


# PHP-Source

&lt;?php

$token = $_POST['token'];<br>
$email = $_POST['email'];<br>
<br>
$con = new mysqli('localhost', 'root', 'aa2019', 'fcm');<br>
<br>
$stmt = $con->prepare("INSERT INTO devices (email, token) VALUES (?, ?)");<br>
$stmt->bind_param("ss", $email, $token);<br>
<br>
$response = array();<br>
<br>
if ($stmt->execute()) {<br>
  $response['error'] = false;<br>
  $response['message'] = 'token stored successfully';<br>
} else {<br>
  $response['error'] = true;<br>
  $response['message'] = $stmt->error;<br>
}<br>
<br>
echo json_encode($response);<br>
<br>
?><br>


# MySQL-Source

CREATE TABLE 'fcm'.'devices' <br>
    ( 'id' INT NOT NULL AUTO_INCREMENT, <br>
      'email' VARCHAR(50) NOT NULL , <br>
      'token' TEXT NOT NULL , <br>
      PRIMARY KEY ('id')) ENGINE = InnoDB;
