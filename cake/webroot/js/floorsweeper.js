// encapsulate my variables
(function() {
	var ctx;
	var c;
	var Ball;
	var Game;
	var Manager;
	var sweeper;
	var floorSweeper;

	window.onload = function() {
		c = document.querySelector('#'+FLRSWPR.id);
		floorSweeper = new Game(FLRSWPR.name, c, FLRSWPR.fps, FLRSWPR.width, FLRSWPR.height);
		sweeper = new Ball(17, 'black', floorSweeper.width/2, floorSweeper.height/2, FLRSWPR.times, floorSweeper);
		floorSweeper.start();
	};
	
	Game = function(name, c, fps, width, height) {
		this.name = name;
		c.setAttribute('width', width);
		c.setAttribute('height', height);
		this.ctx = c.getContext('2d');
		this.interval = 1000 / fps;
		this.width = width;
		this.height = height;
		this.ticker = false;
		this.actors = [];
	};
	
	Game.prototype = {
		
		start: function() {
			var game = this;
			this.ticker = setInterval(function() { game.tick() }, this.interval);
		},
		
		pause: function() {
			clearInterval(this.ticker);
		},
		
		addActor: function(actor) {
			this.actors.push(actor);
		},
		
		tick: function() {
			this.ctx.clearRect(0, 0, this.width, this.height);
			for (var i=0;i<this.actors.length;i++) {
				this.actors[i].moveMe();
				this.actors[i].drawMe(this.ctx);
			}
		}
	};
		
	Ball = function(radius, color, start_x, start_y, frames, game) {

		this.radius = radius;
		this.color = color;
		this.x = start_x;
		this.y = start_y;
		this.frames = frames || [];
		this.currentFrame = 0;
		this.game = game;

		// this.velocity_x = 1.0;
		// this.velocity_y = 1.0;

		// tell the game about me
		if (this.game) {
			this.game.addActor(this);
		}
	};
	
	Ball.prototype = {
	
		moveMe: function () {
				if (this.currentFrame < this.frames.length) {
				this.x = this.frames[this.currentFrame].x;
				this.y = this.frames[this.currentFrame].y;
				this.currentFrame++;
			}
		},
		
		moveMeAutomatic: function() {
		
			var new_x = this.x + this.velocity_x;
			var new_y = this.y + this.velocity_y;
			
			if (new_x > this.game.width || new_x < 0) {
				new_x = this.x;
				this.velocity_x = -(this.velocity_x); 
			}
			if (new_y > this.game.height || new_y < 0) {
				new_y = this.y;
				this.velocity_y = -(this.velocity_y); 
			}
			this.x = new_x;	
			this.y = new_y;	
		},
		
		drawMe: function(ctx) {
			ctx.fillStyle = this.color;
			ctx.moveTo(this.x,this.y);
			ctx.beginPath();
			ctx.arc(this.x, this.y, this.radius, 0, Math.PI+(Math.PI*360)/2, false);
			ctx.fill();
		}
		
	};
	
})();
