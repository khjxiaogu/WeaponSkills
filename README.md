# WeaponSkills  
minecraft bukkit武器装备技能插件库   
WeaponSkills plugin for minecraft.  
可以用于开发各种技能和效果的前置   
A useful library for writing plugin skills and effects without writing a lot of checking and calculating codes  
# How to use  
- Download [PowerNBT](https://github.com/DPOH-VAR/PowerNBT) and put into the plugins/ folder together with this plugin   
- Write or download extensions for this plugin and register skills and effects through apis.  
# 用法  
- 加入[PowerNBT](https://github.com/DPOH-VAR/PowerNBT)作为前置库   
- 编写或者定制本插件的附属来提供各种各样的技能  
# How to write extensions for this plugin 
1. Clone and install this plugin and write  
``` 
 <dependency>  
  <groupId>com.khjxiaogu</groupId>  
  <artifactId>WeaponSkills</artifactId>  
  <version>LATEST</version>  
  <scope>provided</scope>  
 </dependency>  
``` 
in your pom.xml  
2. Get API with `com.khjxiaogu.WeaponSkills.api.WeaponSkillAPI.getAPI()`
3.
   
- Write skill classes implementing interface `com.khjxiaogu.WeaponSkills.skill.Skill` and implement the trigger method as you like and register it with `WeaponSkillAPI.registerSkill(yourSkill)`  
- Write effect with `com.khjxiaogu.WeaponSkills.effect.SimpleEffect` and create a `com.khjxiaogu.WeaponSkills.effect.SimpleEffectFactory` with it to make some simple effects(SimpleEffectFactory class can auto register when created),or you can just implement your custom `EffectFactory` and register it with `WeaponSkillAPI.registerEffectFactory(yourEffectFactory)`  
4. then you can use `/wskill set [name] [level]` to bind skill on an item,an item can have MULTIPLE skills.Or you can use `/wskill effect <player> <effectName> <Level> <milliseconds>` to give player an effect.  
# 如何为本插件编写扩展  
1. clone和mvn install按照本插件到本地库，在附属的Pom.xml里添加如下附属。  
``` 
 <dependency>  
  <groupId>com.khjxiaogu</groupId>  
  <artifactId>WeaponSkills</artifactId>  
  <version>LATEST</version>  
  <scope>provided</scope>  
 </dependency>  
```   
2. 通过 `com.khjxiaogu.WeaponSkills.api.WeaponSkillAPI.getAPI()` 获取插件API。   
3.
   
- 通过实现 `com.khjxiaogu.WeaponSkills.skill.Skill` 接口并且实现你需要的技能触发函数，然后通过 `WeaponSkillAPI.registerSkill(技能)`注册你的技能。  
- 通过实现 `com.khjxiaogu.WeaponSkills.effect.SimpleEffect` 并且以此为参数创建一个 `com.khjxiaogu.WeaponSkills.effect.SimpleEffectFactory` 实例，即可实现插件效果，你也可以通过实现 `EffectFactory` 并用 `WeaponSkillAPI.registerEffectFactory(yourEffectFactory)`注册来实现自定义的效果   
4. 然后你就可以在游戏中使用 `/wskill set [name] [level]` 来给物品设置技能，一个物品可以有多个技能.你也可以使用 `/wskill effect <player> <effectName> <Level> <milliseconds>`以给予玩家插件效果. 
# advanced  
If your extension wants to provide other skill containers,you can use `com.khjxiaogu.WeaponSkills.skill.SkillHook` and write your own skill logics then register it.Look into api source for more details.  
# 高级  
如果你的插件有其他可以提供技能的物品/容器/插槽，你也可以通过实现 `com.khjxiaogu.WeaponSkills.skill.SkillHook`来写你自己的技能检查方法，并注册这个接口来实现自定义的技能获得逻辑。详见api源文件。  
