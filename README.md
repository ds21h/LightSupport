# LightSupport

This is the support jar to be included in the applications LightSwitch and LightAPI. It holds the generic classes. Two of its classes (Setting and Switch) are also to be synchronised with the Android app LightControl.
For an overview please see repository Lights.

The project structure is that of NetBeans 8.2.

History:

Version 1.1 - 07-06-2021
  -   Switched to MariaDB instead of SQLite (only change in connectionstring!)
  -   Deleted obsolete attributes:
      -   In Switch: Type, Group and Point
      -   In Setting: SensorTreshold, MaxSensor and PeriodMin

Version 1.0.1 - 14-01-2019
  -   Missed a Dutch name while renaming. No functional change!

Version 1.0 - 03-12-2018
  -   New package made as a combination of jb.licht.klassen and jb.licht.gegevens
  -   All classes translated into English
