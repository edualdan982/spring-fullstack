function sayHello(name, age) {
  const greeting = `Hola desde una funcion! ${name} edad: ${age}`;
  return greeting;
}

const result = sayHello("edual", 25);
console.log(result);

const sayHelloConst = function (name, age) {
  const greeting = `Hola desde una funcion! ${name} edad: ${age}`;
  return greeting;
};

console.log(sayHelloConst("edual", 25));

const sayHelloArrow = (name, age) => {
  const greeting = `Hola desde una funcion! ${name} edad: ${age}`;
  return greeting;
};

console.log(sayHelloArrow("edual", 25));

const add = (a = 0, b = 0) => a + b;

console.log("Sumando con una función de flecha: "+add(5,10))