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

A tela principal utiliza `ArSceneView` para renderizar a cena e `ArModelNode` para carregar o objeto 3D. O modelo é inicialmente exibido em modo de posicionamento e pode ser ancorado no ambiente pelo usuário.

A aplicação solicita a permissão de câmera em tempo de execução e inicializa a experiência de RA somente após a autorização.

## Como executar

### Requisitos

- Android Studio
- JDK compatível com o projeto
- dispositivo Android com suporte ao ARCore

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
