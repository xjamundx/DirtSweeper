<?php
class GamesController extends AppController {

	public function addTimes() {
		$gameID = null;
		$success = null;
		if (!empty($this->data['GameTime'])) {			
			$xs = explode('|', $this->data['GameTime']['xs']);
			$ys = explode('|', $this->data['GameTime']['ys']);
			$gameTime = array();
			foreach ($xs as $i => $x) {
				$gameTime[] = array(
					'game_id' => $this->data['GameTime']['game_id'],
					'x' => $x,
					'y' => $ys[$i]
				);
			}
			$success = $this->Game->GameTime->saveAll($gameTime);
			$gameID = $this->data['GameTime']['game_id'];
		}
		$this->set(compact('gameID', 'success'));
	}
	
	public function addGame() {
		$gameID = null;
		$success = null;
		if (!empty($this->data['Game'])) {
			$success = $this->Game->save($this->data);
			$success = !empty($success);
			$gameID = $this->Game->id;
		}
		$this->set(compact('gameID', 'success'));
	}
	
	public function index() {
		$games = $this->Game->find('list');
		$this->set('games', $games);
	}
	
	public function watch($gameID = null) {
		$game = $this->Game->find('first', array('conditions' => array('Game.id' => $gameID)));
		$times = $this->Game->times($gameID);
		$this->set(compact('times', 'game'));	
	}
}
?>