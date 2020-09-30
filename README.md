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

<iframe width="560" height="315" src="https://www.youtube.com/embed/qX56WPXhYTA" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
