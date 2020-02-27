# WeaponSkills  
WeaponSkills plugin for minecraft.  
A useful library for writing plugin skills and effects without writing a lot of checking and calculating codes  
# How to use  
- Download powerNBT and put into the plugins/ folder together with this plugin   
- Write or download extensions for this plugin and register skills and effects through apis.  
# How to write extensions for this plugin 
1. Download and install this plugin and write 
``` 
 <dependency>  
  <groupId>com.khjxiaogu</groupId>  
  <artifactId>WeaponSkills</artifactId>  
  <version>1.3.5</version>  
  <scope>provided</scope>  
 </dependency>  
``` 
in your pom.xml  
2. Get API with `com.khjxiaogu.WeaponSkills.api.WeaponSkillAPI.getAPI()`
3.
   
- Write skill classes implementing interface `com.khjxiaogu.WeaponSkills.skill.Skill` and implement the trigger method as you like and register it with `WeaponSkillAPI.registerSkill(yourSkill)`  
- Write effect with `com.khjxiaogu.WeaponSkills.effect.SimpleEffect` and create a `com.khjxiaogu.WeaponSkills.effect.SimpleEffectFactory` with it to make some simple effects(SimpleEffectFactory class can auto register when created),or you can just implement your custom `EffectFactory` and register it with `WeaponSkillAPI.registerEffectFactory(yourEffectFactory)`  
4. then you can use `/wskill set [name] [level]` to bind skill on an item,an item can have MULTIPLE skills.Or you can use `/wskill effect <player> <effectName> <Level> <milliseconds>` to give player an effect.  
# advanced  
If your extension wants to provide other skill containers,you can use `com.khjxiaogu.WeaponSkills.skill.SkillHook` and write your own skill logics then register it.  