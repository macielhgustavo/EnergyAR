# Continuação da aula de RA — Gustavo Maciel

- Fork: https://github.com/macielhgustavo/EnergyAR
- Branch: https://github.com/macielhgustavo/EnergyAR/tree/aula-ra-objetos-interacao-som
- Atualização SceneView: https://github.com/macielhgustavo/EnergyAR/tree/feature/sceneview-energyar

## Nova funcionalidade
Missão de coleta em realidade aumentada. O jogador escolhe o sensor e o filtro na aba de modelos, posiciona cada objeto sobre uma superfície horizontal e toca em **Coletar**. Cada objeto rende 10 pontos uma única vez por rodada. Ao coletar os dois, a missão termina com 20 pontos. O botão Reiniciar missão limpa a pontuação. O estado é mantido ao trocar de tela durante a sessão.

## Implementações
- Dois novos modelos GLB originais: sensor.glb e filtro.glb.
- Coleta protegida por carregamento, ancoragem e rastreamento.
- Contador, conclusão da missão e reinício.
- Som ambiente em loop, com botão para ligar/desligar, pausa ao sair da tela e liberação do player.
- Correção da permissão de câmera, observação do ViewModel e descarte de modelos.
- Compilação, testes e lint pelo GitHub Actions.

## Teste no celular e capturas — pendentes
Não há aparelho físico conectado ao ambiente de desenvolvimento. Portanto, esta seção é um roteiro de teste, não um registro de teste executado.

1. Instale o APK EnergyAR-debug gerado pelo Actions em um Android compatível com ARCore.
2. Permita a câmera. Em uma área iluminada, mova o celular até encontrar uma superfície horizontal.
3. Selecione Sensor, posicione e tire um print com o objeto renderizado em RA antes de coletar.
4. Colete e confira 10 pontos. Tente escolher Sensor novamente: ele não deve pontuar duas vezes.
5. Selecione Filtro, posicione, tire outro print e colete: devem aparecer 20 pontos e missão concluída.
6. Troque de aba e confira a pontuação. Reinicie e confira 0 pontos.
7. Teste som ligado/desligado, saída da tela, segundo plano e retorno.
8. Teste negar a câmera, sair da tela durante o carregamento e voltar.

As duas capturas reais devem ser anexadas à entrega após o teste no aparelho. Imagens ilustrativas não comprovam execução em RA.

## Compatibilidade com a nova branch do professor

O EnergyAR adapta a `feature-sceneview` do AndroidAppTemplateAR (commit `ed416147360d2cf6a823c8fc512513747280e88c`) ao jogo existente. SceneView 2.2.1 substitui a API antiga, e CameraX 1.5.3 permite uma prévia em dispositivos sem ARCore.

Roteiro adicional, ainda pendente de execução em aparelho/emulador:

1. No emulador, confira a indicação de simulação, o modelo 3D e a coleta dos dois objetos.
2. Negue a câmera: deve surgir uma cena 3D sem câmera, com coleta simulada.
3. Em aparelho sem ARCore instalado, confirme a prévia sobre a câmera, sem promessa de ancoragem real.
4. Em aparelho compatível, confirme que Coletar só fica disponível depois da ancoragem e com rastreamento ativo. Interrompa o rastreamento e confira o bloqueio da coleta.
5. Troque de modelo durante o carregamento e navegue repetidamente entre as abas; a seleção mais recente deve prevalecer.
6. Confira pausa do som e retomada da câmera após segundo plano. Se o rastreamento permanecer indisponível por aproximadamente 15 segundos em primeiro plano, deve iniciar a prévia. A câmera indisponível por aproximadamente 8 segundos deve dar lugar à cena sem câmera.

