# CoronaBounce
This project simulates a coronavirus pandemic. People are represented by balls that move in all directions. If a sick ball touches another ball, it itself becomes sick. With this application, you will be able to modify several parameters to make different simulations such as:
- Ball size
- Number of balls
- Number of stationary balls (Represent isolated people. The idea is that stationary balls block the passage of sick balls and limit the spread of the virus)
- Number of sick balls (at the beginning of the simulation)
- Simulation speed
- Ball Speed

## How to install

### Clone the repo
You can clone the repository and launch it with your IDE to compile it. It's a maven project, so you can go in the folder and launch the following commands :
```
mvn package
java -jar target/coronaBounce*.jar
```

### Download the last release
You can download the last release. Then, you just have to launch the following command :
```
java -jar coronaBounce*.jar
```

## How to change settings
Click on the settings button at the top of the window. Then you just need to move all the sliders to change the settings.  When you close this small window, the settings will be automatically saved and applied to the simulation.

## Preview
