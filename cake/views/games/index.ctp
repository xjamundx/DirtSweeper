<?php echo $this->Html->css(array('floorsweeper')); ?>
<h1>Games</h1>

<ul>
<?php foreach ($games as $id => $id): ?>
	<li><a href="/games/watch/<?php echo $id; ?>"><?php echo $id; ?></a></li>
<?php endforeach; ?>
</ul>