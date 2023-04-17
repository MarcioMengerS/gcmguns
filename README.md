# Sistema para Gerenciamento de Materiais GCM:
## Implementado pacote *repository*
### Classe GcmRepository responsável por fazer a conexão com o banco de dados
#### Atributos / Métodos:
* findByNumero;
* findByNomeContaining;

## Implementado pacote *service*
### Classe GcmService
#### Atributos / Métodos:
* Atributo do tipo GcmRepository
* Construtor que recebe objeto do tipo GcmRepository 
* save
* listAll
* findById
* findByNumber
* findByNameContaining
* deleteById

## Implementado pacote *model*
### Classe Gcm
#### Atributos / Métodos:
* Atributos
  - Id (gerado automaticamente pelo banco)
  - Número (Não pode ser nulo)
  - Nome (não pode ser nulo)
  - cpf
  - dataNas
  - dataAdmis
  - email
  - enderecos
* construtor()
* calcularIdade()
* calcularAnosServico()
* getters
* setters

## Implementado pacote *controller*
### Classe GcmController
#### Método / Rota / Verbo:
* Create - localhost:8080/gcm - POST
* FindAll - localhost:8080/gcm - GET
* Delete - localhost:8080/gcm/{id} - DELETE
* Update - localhost:8080/gcm/{id} - PUT
* FindById - localhost:8080/gcm/{id} - GET
* FindByNameContaining - localhost:8080/gcm/name?nome={parteNome} - GET
* FindByNumber - localhost:8080/gcm/num/{numero} - GET
* GetAge - localhost:8080/gcm/age/{numero} - GET
* GetYearsService - localhost:8080/gcm/temp-serv/{numero} - GET

## Implementado pacote *Configuration*
### Classe CorsConfiguration
#### Método:
* addCorsMappings (CorsRegistry registry)


28/03/2023
## MÓDULO openPDF
### Uilizado para produzir documento denominado cautela (comprovante de entrega e devolução de material) no formato pdf.

https://mvnrepository.com/artifact/com.github.librepdf/openpdf

Adicionado ao arquivo pom.xml o trecho de código abaixo:

```
  <dependency>
    <groupId>com.github.librepdf</groupId>
    <artifactId>openpdf</artifactId>
    <version>1.3.29</version>
  </dependency>
```
### As seguintes classes foram incorporadas ao projeto:

* PdfGeneratorService.java no pacote service
    * Conteúdo o documento, como formatação e textos.
* PdfExportController.java no pacote controllers
    * Rota para geração de em formato pdf:
      * GET - localhost:8080/pdf/generate


### Modelo de cautela produzido com openPDF devolvido através da rota designada.

![Cautela](/src/main/java/br/com/gcm/sac/setor_armamento/images/modeloCautela.JPG)
