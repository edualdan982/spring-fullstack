# Comandos de KUBECTL

#### Listar los deployments
```bash
kukectl get deployments
```
Puedes usar el abreviado que **deploy** en vez de **deployments**

#### Listar los pods

```bash
kubectl get pods
```
También funciona con la abreviación **pod** en vez de **pods**

##### Para inspeccionar un elemento de la salida del listado de los pods

```bash
  kubectl describe pod ${columna_name} 
```

##### Para mostrar los logs de un pod de la salida de listado de los pods
```bash
kubectl logs ${columna_name}
```

#### Para exponer en una IP
Existen distintos tipos:

- **--type=ClusterIP**
- **--type=NodePart** es para la comunicación externa
- **--type=LoadBalancer** para poder acceder a las varias replicas siempre y cuando lo soporte

```bash
  kubectl expose deployment ${name_pod} --port=${port} --type=ClusterIP
```

### Para listar todo

```bash
  kubectl get all
```

#### Para listar los servicios

```bash
kubectl get services
```

##### Para describir un servicio

```bash
kubectl describe service ${name_service}
```

#### Para poder levantar un pod con un archivo yaml

```bash
kubectl apply -f .\${name_file}.yaml
```

#### Para create un deploy con imagen desde dockerHub
```bash
kubectl create deployment ${nombre_deploy} --image=USUARIO/NOMBRE_REPO --port=NRO_PUERTO
```

### Para poder generar una ip del cluster con minikube, que es propio de minikube
```bash
minikube service msvc-usuarios --url
```

#### Para cambiar la imagen un pod:
```bash
kubectl set image deployment ${nombre_deploy} usuarios=REPO_USER/REPO:TAG
```

#### Para escalar un deploy con un tipo de red 'LoadBalancer'
```bash
kubectl scale deployment msvc-usuarios --replicas=3
```

#### Para crear un deployment con un configuración perzonlizada
```bash
kubectl create deployment msvc-usuarios --image=USUARIO/NOMBRE_REPO --dry-run=client  -o yaml > nombre_archivo.yaml
```
La bandera --dry-run=client es para que no se ejecute la creación del deployment  

#### Para exportar el YAML de un servicio existente

```bash
kubectl get service mysql8 -o yaml > svc-mysql
```
#### Parar eliminar un deployment

```bash
kubectl delete deployment ${nombre}
```

#### Para eliminar por medio de un archivo

```bash
kubectl delete -f .\deployment-usuarios.yaml
```