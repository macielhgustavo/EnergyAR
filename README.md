# EnergyAR

EnergyAR é um aplicativo Android em Kotlin que utiliza realidade aumentada para posicionar e visualizar objetos 3D no ambiente pela câmera do celular.

O projeto foi desenvolvido como aplicação prática de RA, explorando detecção de superfície, carregamento de modelos `.glb`, interação com objetos virtuais e troca de modelos dentro da própria interface.

## O que o aplicativo faz

- abre uma cena de realidade aumentada usando a câmera do dispositivo;
- permite posicionar um modelo 3D no ambiente;
- mantém o objeto ancorado na cena após o posicionamento;
- permite trocar o objeto exibido pela interface do aplicativo;
- carrega modelos tanto dos assets quanto do armazenamento interno;
- utiliza estado compartilhado entre telas para atualizar o modelo selecionado.

## Tecnologias

- Kotlin
- Android SDK
- ARCore
- SceneView / ARSceneView
- Android Navigation
- ViewModel / LiveData
- View Binding
- Material Design
- Gradle Kotlin DSL

O projeto atualmente utiliza `compileSdk 36`, `targetSdk 36` e suporta dispositivos a partir do Android 7.0 (`minSdk 24`).

## Estrutura principal

```text
app/src/main/
  assets/                         modelos 3D utilizados pela aplicação
  java/com/ifpr/androidapptemplatear/
    MainActivity.kt               navegação principal
    ui/
      CameraFragment.kt           cena AR e posicionamento do modelo
      ChangeObjectFragment.kt     seleção e troca do objeto 3D
      SharedModelViewModel.kt     estado compartilhado entre telas
```

## Como funciona a realidade aumentada

A tela principal utiliza `ARSceneView` e `ModelNode` do SceneView 2.2.1. Em celulares com ARCore instalado, o usuário encontra uma superfície horizontal e posiciona o modelo em um `AnchorNode`.

A aplicação solicita a permissão de câmera em tempo de execução e inicializa a experiência de RA somente após a autorização.

## Como executar

### Requisitos

- Android Studio
- JDK compatível com o projeto
- dispositivo Android; para ancoragem real, é necessário suporte ao ARCore e o serviço instalado

Clone o repositório e abra a pasta no Android Studio. Em seguida, sincronize o Gradle e execute a aplicação em um dispositivo físico compatível com realidade aumentada.

> Para testar corretamente a parte de AR, prefira um dispositivo físico. Emuladores podem não reproduzir todos os recursos de câmera e rastreamento necessários.

## Objetivo técnico

Além da experiência visual, o projeto demonstra integração entre recursos nativos do Android e renderização 3D em tempo real, incluindo:

- gerenciamento de permissões;
- ciclo de vida de `Fragment`;
- navegação entre telas;
- compartilhamento de estado;
- carregamento assíncrono de modelos 3D;
- ancoragem de objetos no espaço físico.

## Status

Projeto funcional em evolução, utilizado para estudo e desenvolvimento de experiências de realidade aumentada no Android.

## Atualização SceneView — novembro de 2026

Adaptação da branch [feature-sceneview do professor Jailton](https://github.com/jailtoncoelho/AndroidAppTemplateAR/tree/feature-sceneview), revisão `ed416147360d2cf6a823c8fc512513747280e88c`, preservando a missão, os modelos e o áudio do EnergyAR.

- ARCore instalado e compatível: ancoragem real em superfícies horizontais.
- Emulador ou dispositivo sem ARCore disponível: prévia 3D sobre a câmera usando CameraX 1.5.3.
- Câmera negada, ausente ou indisponível: cena 3D sem câmera.
- Falha ou falta prolongada de rastreamento: retorno automático à prévia. Não encontrar uma superfície, por si só, não dispara essa troca.
- A prévia identifica a coleta como **simulação**. Ela não comprova funcionamento em RA.
- Em RA, a coleta exige um objeto ancorado e rastreamento ativo; a pontuação continua limitada a uma coleta por objeto por rodada.

O som pausa em segundo plano e é liberado ao sair da tela. A câmera é opcional no manifesto. A compilação, os testes e o lint são executados pelo workflow Android, que também disponibiliza o APK de depuração.

## Continuação — missão de coleta

Dois novos modelos GLB (sensor e filtro), coleta com 10 pontos por objeto e som ambiente controlável. Consulte [a descrição e o roteiro de teste no celular](docs/Entrega_RA.md). O teste físico e as capturas reais de RA permanecem pendentes.

