<?php
class Game extends AppModel {
	var $hasMany = array('GameTime');
	
	public function times($gameID = null) {
		$times = array();
		if ($gameID) {
			$game_time = $this->GameTime->find('all', array(
				'fields' => array('GameTime.x', 'GameTime.y'),
				'conditions' => array('GameTime.game_id' => $gameID)
			));
			if (!empty($game_time)) {
				foreach ($game_time as $time) {
					$times[] = array(
						'x' => $time['GameTime']['x'],
						'y' => $time['GameTime']['y'],
					);
						
				}
			}
		}
		return $times;
	}
}