# 🎨 Instruções para Ícone do Energy AR

## O que fazer (no Android Studio):
1. Abra o projeto no Android Studio
2. Clique com o botão direito na pasta `res` → **New** → **Image Asset**
3. Em **Icon Type**: `Launcher Icons (Adaptive and Legacy)`
4. Em **Source Asset**: escolha **Image** e selecione um arquivo PNG **512x512** com tema de **energia/indústria**  
   Exemplos: raio ⚡, turbina eólica 🌬️, painel solar ☀️, fábrica 🏭, transformador ⚡
   - Você pode criar um no Figma, Canva, ou baixar de sites livres (ex: https://www.flaticon.com, https://iconduck.com, https://github.com/Templarian/MaterialDesign)
   - O arquivo deve ser **quadrado (512x512)** e **PNG com transparência**
5. Em **Name**: mantenha `ic_launcher`
6. Clique **Next** → **Finish**
7. O Android Studio vai gerar automaticamente todos os tamanhos (mipmap-*) e ícones adaptativos (XML em mipmap-anydpi-v26)

## ✅ Verificação:
- O `AndroidManifest.xml` já usa `android:icon="@mipmap/ic_launcher_round"` e `android:roundIcon="@mipmap/ic_launcher_round"` — **não precisa mudar nada no código**
- Após gerar, teste no emulador/dispositivo: o ícone do app deve aparecer com seu novo desenho

## ⚠️ Importante:
- NÃO edite os arquivos .webp manualmente nas pastas mipmap-*
- SEMPRE use o Image Asset Studio — ele cria os tamanhos certos, máscaras adaptativas e fallback legacy
- Se quiser um ícone redondo diferente do quadrado, preencha também o campo "Foreground Layer" → "Layer Name" → Source Asset (separado) para o round icon

## Fontes gratuitas recomendadas para ícone de energia:
- https://github.com/Templarian/MaterialDesign-SVG (procure: lightning-bolt, factory, solar-power, wind-turbine, transmission-tower)
- https://iconduck.com/icons/sets/material-design/energy
- https://www.flaticon.com/search?word=energy%20industrial

## Após fazer isso:
- Commit as mudanças nos mipmap-*
- Push para a branch `feature/energy-ar-customization`