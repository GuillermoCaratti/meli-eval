# Mutant Finder ™ 
This project verifies if your DNA is **OK** _...or you are a human_.

## Installation
### Requirements
* Java 11 or superior
* Git
> Read [this](./README.md) to better understand the technologies applied in this  project

#### Download the code and start 

Just run
```shell script
git clone https://github.com/GuillermoCaratti/meli-eval.git
cd meli-eval
./gradlew bootRun
```
> Windows users may use `gradlew` instead of `./gradlew` 

## Usage
#### check if you are a mutant :passport_control:

```shell script
curl --request POST \
  --url http://localhost:8080/mutant \
  --header 'content-type: application/json' \
  --data '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'
```

> please change the example for your DNA
#### or ... GET STATISTICS! :chart_with_upwards_trend:
```shell script
curl --request GET \
  --url http://localhost:8080/stats
```

> **TIP** if you use a GUI REST client like *Insomnia* or *Postman* you can paste that code 
> for request auto-generation   

## Generate build

Just run:
```shell script
./gradlew build
```

... and you will have the application in `build/libs/eval-0.0.1-SNAPSHOT.jar` and 
 the test coverages will be in  `build/reports/jacoco/test/html/index.html`.

This project counts with more than ~~_80%_~~ **90%** of code coverage ! :ribbon: 

## Demo
A demo is deployed in https://pure-sea-07386.herokuapp.com/

## Development

You will need to install [Lombok](https://projectlombok.org/) plugin for your ide:

* [IntelliJ](https://projectlombok.org/setup/eclipse) 
* [Eclipse](https://projectlombok.org/setup/intellij) 
* [VsCode](https://projectlombok.org/setup/vscode) 

## Links

* [Issue tracker](https://github.com/GuillermoCaratti/meli-eval/issues)
* [Github project kanban](https://github.com/GuillermoCaratti/meli-eval/projects/1)

## License
This code is released under [the MIT license][MIT_License]  


[MIT_License]: https://en.wikipedia.org/wiki/MIT_License