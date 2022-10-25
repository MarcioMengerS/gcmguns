# Gerenciamento de Materiais GCM:
## Implementado pacote *repository*
### Classe GcmRepository
#### Atributos / Métodos:
findByNumero;
findByNomeContaining;

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
#### Atributos / Métodos:
* salvar (localhost:8080/save)
* listarTodos (localhost:8080/listall)
* excluirPorId (localhost:8080/delete/{id})
* alterar (localhost:8080/modify)
* buscarPorId (localhost:8080/find/{id})
* buscarPorParteNome (localhost:8080/name)
* buscarGcmPorNum(localhost:8080/{numero})
* devolveIdade (localhost:8080/idade/{numero})
* calcularTempoServ (localhost:8080/temp-serv/{numero})

## Implementado pacote *Configuration*
### Classe CorsConfiguration
#### Método:
* addCorsMappings (CorsRegistry registry)