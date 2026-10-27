# Continuação da aula de RA — Gustavo Maciel

- Fork: https://github.com/macielhgustavo/EnergyAR
- Branch: https://github.com/macielhgustavo/EnergyAR/tree/aula-ra-objetos-interacao-som

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
