<h1>ToolSwap</h1>
<p>A Bukkit plugin that automatically switches the tool in a players hand depending on what block/entity that player interacts with. See <a href="https://github.com/mmonkey/ToolSwap/tree/master#swapping">Swapping</a> for details on tool swapping.</p>
<p>Version: v1.0</p>

<h2>Installing & Upgrading</h2>
<ol>
  <li>Download ToolSwap.</li>
  <li>Unzip ToolSwap.zip.</li>
  <li>Drag ToolSwap.jar into your "craftbukkit/plugins" folder <em>&ndash; replace ToolSwap.jar when upgrading.</em></li>
</ol>

<h2>Commands</h2>
<p>ToolSwap is very simple to use, in fact there is only one command!</p>
<ul>
  <li><b>/ToolSwap [on/off]</b> <em>&ndash; this turns ToolSwap on or off.<em></li>
</ul>
<p>Please note that ToolSwap is <b>disabled by default.</b></p>

<h2>Swapping</h2>

<h3>The Right Tool for the Job</h3>
<ul>
  <li><b>ToolSwap &ndash;</b> When a player left-clicks on a block that can be mined, ToolSwap will determine if the correct tool exists in that player's inventory.  If so, the item in-hand will be swapped with the correct tool in that player's inventory.</li>
  <li><b>Auto-Torch &ndash;</b> When a player has a pickaxe or shovel in-hand, and right-clicks a block that a torch can be placed on, ToolSwap will determine if torches are in that player's inventory. If so, the item in-hand will be switched with the torches in that player's inventory.</li>
  <li><b>Fighting &ndash;</b> When a player left-clicks on an enemy mob, ToolSwap will determine if a sword is in that player's inventory. If so, the item in-hand will be swapped with a sword.</li>
  <li><b>Tool Breaking &ndash;</b> If a tool breaks while ToolSwap is on, ToolSwap will swap to the next available correct tool in that player's inventory.</li>
</ul>
    
<h3>Multiple Tools, Not a Problem</h3>
<p>Let's say that a player clicks on a cobblestone block, and has multiple pickaxes in their inventory, <b>how does ToolSwap determine what pickaxe to switch to?</b></p>
<p>When swapping, ToolSwap will find the <b>first</b> correct tool in that player's inventory:</p>

<h4>Order of First to Last</h4>
<ol>
  <li>The 'HotBar" from left to right.</li>
  <li>The rest of the player's inventory from left to right, top to bottom</li>
</ol>
<p>This means that the left-most slot in the "HotBar", is the <em>first</em> item in a player's inventory. The item in right-most slot on the bottom row of a player's inventory is the <em>last</em> item in a player's inventory.</p>

<h3>Special Cases</h3>
<ul>
  <li>When a player has a bow in-hand, and that player clicks on a block that can be mined, ToolSwap <b>will not</b> swap to the correct tool for mining. However, if the player has a bow in-hand, and that player clicks on an enemy mob, ToolSwap <b>will</b> swap to the first sword in that player's inventory.</li>
  <li>If ToolSwap swaps an item in a player's "HotBar," instead of swapping the items in the bar, ToolSwap will change the selected slot of the correct item. This makes players that are particular about their "HotBar" inventory quite happy.</li>
  <li>Some ores/blocks require a certain level of pickaxe to mine, ToolSwap will only swap to the required level (or higher) pickaxe needed to mine that ore/block.</li>
</ul>

<h2>Future Releases</h2>
<ul>
  <li>Add Permissions</li>
  <li>Configurable Preferences &ndash; set which tools to use on which blocks.</li>
  <li>Per-player Preferences &ndash; player's can set the tool in-hand as the preferred tool to switch to, when mining specific ores/blocks.</li>
</ul>

<h2>Credits</h2>
<p>Author: silvermmonkey A.K.A. <a href="http://forums.bukkit.org/members/mmonkey.90802399/">mmonkey</a><br>
Testers: Desaxt01, GrahamCracker4m, HuskerMath and e_dick.</p>

<h2>ChangeLog</h2>
<ul>
  <li>v1.0 <em>&mdash; Initial release.</em></li>
</ul>