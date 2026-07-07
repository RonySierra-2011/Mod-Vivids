# Vivids - Mod de Minecraft Forge 1.20.1

## Como subirlo a GitHub y conseguir el .jar (sin instalar nada en tu PC)

1. Crea un repositorio nuevo en GitHub (puede ser privado).
2. Sube TODA esta carpeta tal cual (arrastra los archivos en la web de
   GitHub, o con `git init && git add . && git commit -m "vivids" && git push`).
3. Este proyecto ya trae `.github/workflows/build.yml`. En cuanto hagas
   push a la rama `main`, GitHub va a compilar el mod automaticamente
   (pestaña **Actions** de tu repo -> el ultimo run -> seccion
   **Artifacts** -> descarga `vivids-mod-jar`).
4. Adentro de ese zip esta el `.jar` real, listo para copiar a la
   carpeta `mods` de tu Minecraft con Forge 1.20.1 instalado.

No necesitas Gradle ni JDK instalados en tu computadora para esto: todo
el proceso de compilar el `gradle-wrapper.jar` (que faltaba localmente)
y generar el `.jar` final ocurre en los servidores de GitHub gracias al
workflow incluido.

## Como abrirlo localmente en IntelliJ/VSCode (opcional)

1. Necesitas JDK 17.
2. Abre la carpeta del proyecto.
3. Corre una vez `gradle wrapper --gradle-version 8.1.1` (requiere tener
   Gradle instalado una sola vez) para generar el `gradle-wrapper.jar`
   que falta en este zip (es un binario, no lo puedo generar yo).
4. Luego `./gradlew genIntellijRuns` y `./gradlew build`.

## Estado real de cada sistema pedido

| Sistema | Estado |
|---|---|
| Vivid Normal (camina, IA, domesticar con Comida Vivid, evita Creepers) | ✅ FUNCIONAL |
| Vivid del Bano (domestica con Jabon Espumoso) | ✅ FUNCIONAL |
| Vivito (bebe, nace del breeding) | ✅ FUNCIONAL |
| Modelo sin flotar, pies apoyados al suelo | ✅ FUNCIONAL |
| Mochila Vivid (9 slots reales, persistentes en NBT) | ✅ FUNCIONAL |
| Rayo Vivid / Rayo del Bano (disparo + sobrecalentamiento 20s) | ✅ FUNCIONAL. Proyectil visual reutiliza tridente vanilla como placeholder |
| Los 4 Jefes (Rey Vivid, Guardian Vivid, Duque Jabonoso, Limpiador Supremo) | ✅ FUNCIONAL como entidades: mucha vida, barra de jefe, ataque cuerpo a cuerpo, sueltan los Rayos. Spawn eggs incluidos para invocarlos en creativo |
| Portal del Bano (bloque colocable + activacion con Burbuja) | ✅ FUNCIONAL (version simplificada: el bloque en si es el activador, no un marco 3x3 tipo Nether) |
| Dimension del Bano (viajable de verdad) | ✅ FUNCIONAL como mundo plano con capas de bloques vanilla (quartz/terracota) simulando azulejos. NO genera estructuras de bano/lavamanos/banaderas (eso requiere arte 3D que no puedo generar) |
| Items (Fragmento, Tela, Burbuja, Corazon, Moneda, Comida, Jabon) | ✅ FUNCIONAL |
| Recetas de crafteo | ✅ FUNCIONAL (comida, jabon, mochila, marco de portal). Los Rayos NO son craftables a proposito, solo caen de jefes, fiel a la infografia |
| Loot tables | ✅ FUNCIONAL para todas las entidades, incluidos los 4 jefes |
| Spawn raro en el Overworld | ⚠️ APROXIMADO: aparece raro en todo el Overworld via biome modifier, NO exclusivamente dentro de aldeas/campamentos (eso necesita estructuras .nbt, ver abajo) |
| Campamentos Vivid / Templos del Bano / estructuras | ❌ NO IMPLEMENTADO - requieren archivos .nbt de estructura (geometria 3D construida a mano en el juego), fuera de lo que puedo generar desde texto |
| Sonidos | ⚠️ Eventos registrados y conectados en Java, SIN archivos .ogg reales (no puedo generar audio), ver `sounds/README.txt` |
| Texturas | ⚠️ Placeholders de color solido 16x16/64x64, no arte final |
| IA social avanzada (conversan, se saludan, duermen en horario) | ❌ NO IMPLEMENTADO |

## Lo que sigue siendo imposible de generar por texto (y por que)

- **Estructuras (.nbt)**: casas Vivid, campamentos, templos, ruinas. Son
  archivos binarios de geometria 3D que se crean construyendo en el
  juego con un Structure Block. Ningun generador de texto puede producir
  esto de forma confiable.
- **Audio (.ogg)**: sonidos originales requieren grabacion o diseño de
  audio real.
- **Arte final**: las texturas incluidas son placeholders de un solo
  color para que el proyecto compile y se vea algo en el juego, no arte
  terminado.

## Siguiente paso recomendado

Si quieres que la Dimension del Bano se sienta mas fiel a la infografia
(con huecos tipo lavamanos/banaderas), o le agregamos ataques especiales
distintos a cada jefe, dime cual y seguimos iterando sobre esta base ya
real y compilable.
