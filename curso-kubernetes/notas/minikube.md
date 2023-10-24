# SO Windows 10 o 11 pro
#### Para iniciar el minikube con hyper-v

```bash
  minikube start --driver=hyperv
```
#### Verifique que los servicios estan todos corriendo
```bash
  minikube status
```
Ejemplo de repuesta minikube:

| Parametro    | Estado    |
| :--------    | :-------  |
| `type`       | `Running` |
| `host`       | `Running` |
| `kubelet`    | `Running` |
| `apiserver`  | `Running` |
| `kubeconfig` | `Running` |

### Poner en default el driver de Hyper-V

```bash
  minikube config set driver hyperv
```
