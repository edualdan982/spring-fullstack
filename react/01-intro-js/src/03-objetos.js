const invoce = {
  id: 10,
  name: 'Compras de oficina',
  date: new Date(),
  client: {
    id: 2,
    name: 'Jhon',
    lastNmae: 'Doe',
    age: 20
  },
  total: 1000,
  greeting: ()=>{
    return `Hola ${invoce.client.name}`;
  }
};

invoce.total = 5000;
console.log(invoce);

const greeting = invoce.greeting();
console.log(greeting);