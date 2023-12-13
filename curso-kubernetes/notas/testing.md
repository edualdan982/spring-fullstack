### Prueba del service url msvc-auth
La url puede cambiar ya que este esta sujeto al cluster de asignación para exporner el servicio.
```
http://172.20.28.49:32466/oauth/authorization/msvc-usuarios-client
```


Este va al servidor de autorizacion:
http://172.31.104.21:31736/oauth2/authorize?response_type=code&client_id=usuarios-client&scope=read%20write&state=7U6IN4nQD7_lsgn2pOZIWNWZrRdK5OemrXmqL5VKjso%3D&redirect_uri=http://172.31.104.21:30183/authorized&continue

--

http://172.20.145.74:31736/oauth2/authorize?response_type=code&client_id=usuarios-client&scope=read%20write&state=Qfnhcx9uYw_kECXUgAxamcRhXALkq-4hT6Uhc8VzFKM%3D&redirect_uri=http://172.20.145.74:30183/authorized&continue

http://172.20.145.74:30183/authorized?code=WGvZIeeFkHXq-3nKI6LeezVjpEC_Cqe3EZlNW4Wj5aiMUJCSNmkOaUQjG1-e0J-SqVn0gFBg31hiF55FhxLys4s-Iui5v1fIhxZv3E8BeyqRU22lh7z_3fKRVuiSumqQ&state=7U6IN4nQD7_lsgn2pOZIWNWZrRdK5OemrXmqL5VKjso%3D