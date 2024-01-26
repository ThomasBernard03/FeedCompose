# FeedCompose


## Features


## Architecture

Voici l'architecture utilisée pour ce projet :

- commons
- data 
- domain
- presentation

### Presentation

- commons
- data 
- domain
- presentation
    - feature
        - FeatureScreen
        - FeatureUiState
        - FeatureEvent
        - FeatureViewModel

Cette couche est responsable de l'affichage de nos données. C'est la couche visible de l'application. Dans cette couche nous retrouvons un dossier par page de l'application (ou feature). Dans chacun de ces dossier nous retrouvons 4 fichiers :

- Screen : Fichier comportant une méthode @Composable Screen ayant en paramètre un State et un callback onEvent. Et une méthode preview permettant d'afficher une preview de la page.

- State : État de notre page, dans cette data class on retourve toutes les informations liées à notre feature.

- Event : sealed class contenant tous les évenements propagés depuis le Screen. Ces events sont interprétés par notre ViewModel.

- ViewModel : Interprète les évèmements propagés par l'interface utilisateurs et update le state (Mettant à jour l'interface utilisateurs). Dans le viewmodel, nous injectons nos usecases.

### Domain

- commons
- data 
- domain
    - models
    - usecases
    - wrappers
- presentation

Dans la couche domain nous retrouvons nos models, ce sont des classes ou des data classes qui sont transmisent à notre couche de présentation. Ces modèles sont simples et continnent uniquement des types simples (String, Int, Boolean...).

Les wrappers sont des entités surchargés qui contiennent des données plus complexes, listes imbriquées etc... Ces wrappers permettent de faciliter l'affichage de nos données.

Les uses cases contiennent la logique de notre application, dans ces derniers nous injectons nos repositories. Les méthodes de nos uses cases retournent des wrappers ou alors des models. Les uses cases retournent des Resources. Ces resources contiennent le résultat de nos requètes en cas de succès, dans d'autres cas ces Resources contiennent le message d'erreur approprié.

### Data

- commons
- data 
    - local
    - remote
    - repositories
- domain
- presentation

La couche data est responsable de la récupération des données. Elle peut faire le lien entre les API et notre base de données.

Dans le package local, nous retrouvons nos dao, nos entités et la configuration de notre base de données.

Dans le package remote nous retrouvons nos routes api de retrofit et nos DAO qui permettent de déserialiser nos données.

Dans le package repositories, on retrouve les interfaces de nos repositories et leurs implémentations. Ces repositories doivent récupérer des données des API et les stocker en base de données. Ils retournent aussi des données lorsque les uses cases les demandent.


### Commons

La couche commons contient différentes classes et méthodes utilisatbles dans n'importe quelle couche applicative.

Elle contient des helpers, et des constants.