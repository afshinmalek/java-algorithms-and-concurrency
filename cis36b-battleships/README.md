<div align="center">

# 🚢 Battleships - Networked GUI Game

### Java · Swing · Socket Networking

Swing Battleships on 25x25 player and tracking grids, with socket networking for server/client play.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-ED8B00?style=for-the-badge&logoColor=white)
![Networking](https://img.shields.io/badge/Socket_Networking-ED8B00?style=for-the-badge&logoColor=white)

</div>

---

## ✨ Features

| Feature | Details |
| --- | --- |
| 🎯 25x25 grids | Separate player grid and tracking grid |
| 🛜 Socket play | One player hosts as server, the other joins as client |
| 💥 Hit / miss / sunk / win | Full shot-resolution protocol with grid state updates |
| 🔄 Turn synchronization | Turns alternate and stay in sync over the socket |

## ▶️ Run

```bash
javac Battleships.java
java Battleships server localhost 5000
# In a second terminal:
java Battleships client localhost 5000
```

> Compile each project folder on its own. Some projects define classes with the same names (for example, `Edge` and `G50` appear in both the Shortest Path and MST projects), so do not compile the whole repo together.
