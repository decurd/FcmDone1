## Memo

FIREBASE, Volley

## PHP-Source

```html
<?php

$token = $_POST['token'];
$email = $_POST['email'];

$con = new mysqli('localhost', 'root', 'aa2019', 'fcm');

$stmt = $con->prepare("INSERT INTO devices (email, token) VALUES (?, ?)");
$stmt->bind_param("ss", $email, $token);

$response = array();

if ($stmt->execute()) {
  $response['error'] = false;
  $response['message'] = 'token stored successfully';
} else {
  $response['error'] = true;
  $response['message'] = $stmt->error;
}

echo json_encode($response);

?>
```


## MySQL-Source

```html
CREATE TABLE 'fcm'.'devices' 
( 
  'id' INT NOT NULL AUTO_INCREMENT, 
  'email' VARCHAR(50) NOT NULL , 
  'token' TEXT NOT NULL , 
  PRIMARY KEY ('id')
) ENGINE = InnoDB;
```
