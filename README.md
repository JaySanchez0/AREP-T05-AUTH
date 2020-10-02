# AREP-T05-AUTH

### Presentado por:

Jeisson G. Sanchez Ramos

### Generar las llaves

~~~
    keytool -genkeypair -alias authappkeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore authkeystore.p12 -validity 3650
~~~


### Auth for virtual machine:

[docker hub](https://hub.docker.com/r/jsanchez0/arep-t05-auth)


### Video demostracion

[ver](https://www.youtube.com/watch?v=qX56WPXhYTA&feature=youtu.be)


### Nota: 
En produccion se dejo en el metodo ssl descomentado:


~~~
 javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                     (hostname, sslSession) -> true);
~~~

ya que este omite el host dado que este certificado no fue emitido para un host permitido, se modifico 
para que permitiera su uso exclusivo en localhost.


En las imagenes de docker se encuentra con esta linea, si desea correrlo porfavor ejecute este y el servicio de notas.


En el produccion se dejo en el constructor del servicio recibir el host:puerto como variable de entorno, aqui por la modificacion se va a dejar quemado localhost temporalmente, como vera esta linea de recibir la variable de entorno.


La imagen de docker funciona para cualquier host ya que solo valida el certificado y no el host.





