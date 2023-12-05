#### Si quieres realizar un retraso en un comando RUN puedes hacerlo con el comando:
```bash
RUN sleep 10 && COMANDO_QUE_LE_SIGUE
```

#### Uso del host.docker.internal
Si estas trabajando en docker en un Sistema Operativo Windows o Mac, se puede usar para referenciar a localhost con la ```host.docker.internal```.
Este lo que hace trabaja como una resolución del host externo de docker donde se trabaja.

Pero si esta en Linux este no puede funcionar, ya que este tiene un distinto trato con la resulciones de los host, para estos casos use el:
```http://172.17.0.1``` que es el mas común, ya que en linux docker levanta una interfaz de red para poder administrar las salidas al host externo.