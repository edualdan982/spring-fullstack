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

#### Para listar los volumenes
```bash
kubectl get sc
```

#### Para darle permiso al spring-cloud en kubectl
```bash
kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
```

El comando kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default realiza lo siguiente:

kubectl create clusterrolebinding admin: Crea un ClusterRoleBinding llamado admin. Un ClusterRoleBinding es un objeto que otorga permisos definidos en un ClusterRole a usuarios, grupos o ServiceAccounts1.

--clusterrole=cluster-admin: Especifica que el ClusterRole asociado es cluster-admin, que es un rol predefinido en Kubernetes que otorga permisos completos sobre todos los recursos y objetos dentro del clúster1.

--serviceaccount=default:default: Asigna el ClusterRoleBinding a la cuenta de servicio default en el espacio de nombres default. Las cuentas de servicio son utilizadas por los procesos de los pods para interactuar con la API de Kubernetes1.

Por lo tanto, este comando otorga a la cuenta de servicio default en el espacio de nombres default todos los permisos sobre todos los recursos y objetos dentro del clúster. Esto puede ser útil para tareas de administración, pero también puede ser un riesgo de seguridad si la cuenta de servicio se utiliza de manera inapropiada o se ve comprometida1.

Para eliminar todos los recursos en un cluster de Kubernetes con kubectl, puedes usar el comando delete con el flag --all. Aquí te dejo un ejemplo de cómo hacerlo para diferentes tipos de recursos:

#### Eliminar todos los pods
```bash
kubectl delete pods --all
```

#### Eliminar todos los servicios
```bash
kubectl delete services --all
```

#### Eliminar todos los deployments
```bash
kubectl delete deployments --all
```

Por favor, ten en cuenta que este comando eliminará todos los recursos del espacio de nombres actual. Si quieres eliminar todos los recursos en todos los espacios de nombres, puedes usar el flag --all-namespaces.

#### Eliminar todos los recursos en todos los espacios de nombres
```bash 
kubectl delete pods,services,deployments --all --all-namespaces
```
Advertencia: Este comando eliminará todos tus recursos de Kubernetes, así que úsalo con precaución. Asegúrate de entender completamente lo que este comando hace antes de ejecutarlo.

