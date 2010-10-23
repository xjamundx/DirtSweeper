<article>
	<h1>Mobile web application basics.</h1>
	<nav>
	<ul>
		<li><a href="#tools">Basic coding tools</a></li>
		<li><a href="#design">Design</a></li>
		<li><a href="#structure">Basic Structure</a></li>
		<li><a href="#meta">Icons, full-screen mode, and other options</a></li>
		<li><a href="ui">User Interface Building Blocks</a></li>
		<li><a href="together">Putting it all together</a></li>
		<li><a href="links">Available resources</a></li>
	</ul>
	</nav>
</article>


<article id="tools">
	<a name="tools"><h1>Basic coding tools</h1></a>
	<p>I use a Mac and do most of my development in <a href="http://www.panic.com/coda">Coda</a>. It cost $99 and has saved me a ton of headache. It offers syntax highlighting for HTML, PHP, CSS, and Javascript as well as code completion SVN integration, easy file management, some cool extensions, and many other features. A lot of other Mac users I know love <a href="http://macromates.com/">TextMate</a>, which is a pretty straight forward editor that offers many helpful programmer tools. It doesn't really matter what you use, but I find the syntax highlighting and coding hinting speeds me up and helps me avoid making really easy mistakes.</p>
	
	<p>In addition to my IDE (integrated development environment) it's nice to have some handy-dandy web browsers to use for testing my mobile site. Firefox is an important browser to test in, because while it supports many web standards it does not support many of the propriety CSS properties found in the Webkit-based browsers (Safari, Mobile Safari, Android, etc). So it can give you a better idea of what your app might look like on non-webkit browsers. I use the <a href="http:/www.firebug.com">Firebug</a> extension too, which really helps! In addition to Firefox I always test in either Chrome 5 or Safari 5. To me they're very similar. The inspector which can be turned on is very similar to Firebug in that it allows you to view CSS properties, log javascript messages and perform live updates to the site.</p>
	
	<p>Emulators are also important. So far I have used the Android emulator, the webOS emulator, the iPhone emulator, and the Opera Mobile emulator. Now I actually have these devices, which is nice as sometimes the emulators are not the same as the real thing. As of Blackberry 5.x you can only run the emulator reliably on Windows, which is a shame. The Windows 7 phone emulator also works exclusively on Windows. I'm working on getting a laptop up and running for this purpose, but for now I'm not expecting those phones to have a perfect experience. Without testing with them, neither should you.</p>
</article>

<article id="design">
	<a name="design">Design</a>
	<p>360 x 480</p>
</article>

<article id="structure">
	<a name="structure"><h1>Basic structure</h1></a>
	<p>There are a number of schools of thought about how you might want to structure the files in your mobile application. If you're using a framework like jQtouch or SenchaTouch you're likely developing an iPhone-centric web-app you probably will need only 3 files:</p>
	<ul>
		<li>index.html</li>
		<li>style.css</li>
		<li>main.js</li>
	</ul>
	<p>However, not everyone is making those types of apps and if you're just getting started it might make more sense to try something like this:</p>
	<ul>
		<li>index.html</li>
		<li>page1.html</li>
		<li>page2.html</li>
		<li>...</li>
		<li>style.css</li>
		<li>main.js</li>
	</ul>
	<p>Using this second approach you can guarantee your back button will work on android and other platforms and with a few tricks still enable full screen mode on the iPhone.</p>
</article>

<article id="meta">
	<a name="meta"><h1>Icons, full-screen mode, and other options</h1></a>
	<a href="http://developer.apple.com/safari/library/documentation/appleapplications/reference/safarihtmlref/articles/metatags.html">Safari supported meta tags.</a>
</article>

<article id="ui">
	<a name="ui"><h1>UI Building Blocks</h1></a>

</article>

<article id="together">
	<a name="together"><h1>Putting it all together</h1></a>

</article>

<article id="resources">
	<a name="resources"><h1>Available resources</h1></a>
	<ul>
		<a href="http://dev.opera.com/articles/view/the-mobile-web-optimization-guide">Opera Mobile Web Optimization Guide</a>	
		<a href="http://www.w3.org/TR/mobile-bp/">W3C Mobile Web Best Practices</a>
		<a href="http://www.w3.org/TR/mobile-bp/">W3C Mobile Web Best Practices</a>
		<a href="http://sixrevisions.com/web-development/html5-iphone-app/">Six Revisions: How to make an HTML5 iPhone App</a>
	</ul>
</article>
