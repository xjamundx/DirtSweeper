<?php echo $this->Html->css(array('floorsweeper')); ?>
<canvas id="floorSweeper" width="<?php echo $game['Game']['width']; ?>"  height="<?php echo $game['Game']['height']; ?>"></canvas>
<script type='text/javascript'>
	var FLRSWPR = {
		id:'floorSweeper',
		name:'Floor Sweeper',
		width:<?php echo $game['Game']['width']; ?>,
		height:<?php echo $game['Game']['height']; ?>,
		fps:10,
		background:'black',
		times:<?php echo json_encode($times); ?>
	};
</script>
<?php echo $this->Html->script(array('floorsweeper')); ?>