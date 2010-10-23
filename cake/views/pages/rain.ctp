<h1>Getting modern with Modernizr.js</h1>

<h2>Intro</h2>
<p>Rain recently built a mobile website called the Mobile App Cheat Sheet, which can be viewed on your Android or iPhone phones at this URL: <a href="http://m.mediarain.com">http://m.mediarain.com</a>. It takes advantage of a whole host of modern technologies that makes it successful. In this blog post I want to talk about one of the libraries we used, Modernizr.js by Paul Irish, that allowed us to enhance the experience for mobile browsers with the latest features.
</p>

<h2>Progressive enhancement</h2>
<p>Progressive enhancement is a design pattern that lets your website work well in older browsers, while adding the latest technology to the newest browsers. In the case of the mobile app cheat sheet we really only targeted Android and iOS, but even between those two webkit browsers levels of support for specific features are lacking. We use the progressive enhancement design pattern by taking advantage of the feature detection functionality of Modernizr.js.  </p>

<h2>Feature detection vs. User-agent Detection</h2>
<p>For a long time serving different browsers different things relied on user-agent detection. User-agent detection is supposed to make it easy to determine who should or shouldn't be able to view your site, but if the web browser you are preventing improves faster than you improve your user-agent detection you can easily tell someone who should fully be able to view your site that their browser isn't good enough and that they need to upgrade. I'm sure you know what I'm talking about. Apple's recently HTML5 open-ness demos that only worked on Safari are a good example of a bad-use of user-agent detection. Feature detection on the other hand is a cross-browser way to determine if the browser can support specific features of your site. You might use it to determine if a specific browser supports CSS3 rounded corners and HTML5 geo-location. If not you provie a simple work-around. Instead of blocking people out, you let in and provide them with a workable web-page, that just might not be as pretty or exciting is it would had they access to a newer web-browser.</p>

<h2>How does Modernizr work?</h2>
<p>So how does Modernizer do feature detection? Simply include the 5kb library in your header and add a class of "no-js" to your base &lt;html&gt; tag. When your page is loaded into the browser Modernizr will do its magic, replacing the no-js class with a number of classes that represent capabilities available to that browser. So in our rounded corners example, modernizr will have added the "borderradius" class to your html tag if it is supported or the "no-borderradius" class if it is not. All you need to safely implement rounded corners and a workaround for non-supporting browsers is to reference this class in your CSS:</p>

<pre class="css">
.borderradius a {
	border-radius: 5px;
	border: 2px solid blue;
}
 	
.no-borderradius a {
	background: url("/img/button_border.png");
}
</pre>

<h2>Extending Modernizr</h2>

<p>Modernizr supports a whole host of HTML5 and CSS3 properties. It also allows you to check for these properties directly in Javascript. In the above example we could also look like for the Modernizr.borderradius in Javascript, which would return either true or false depending on support for the feature. In our mobile website we extended Modernizr to check whether or not we were in the iOS full-screen mode. This allowed us to compensate for the lack of a back button found when running iOS in full screen mode. (If you don't know how to do this. On your iPhone head to m.mediarain.com, click the settings button and select "Add To Homescreen". When launched again you will see the site running similar to a native application with no back button or toolbar.) It's very easy to extend Modernizr. </p>

<pre class="javascript">
// check if it's a web-app vs web-page
Modernizr.addTest('standalone',function(){
	return window.navigator.standalone;
});
</pre>

<h2>How we used Modernizr</h2>

<p>Our site needed to use Modernizr in two ways. 1) We used it to see if the user was in full screen mode. 2) We used it to determine if the browser had support for SVG graphics. SVG graphics are especially important on newer iOS devices that have the retina display. Here's an example two images zoomed in 3x to simulate how an SVG image might compare to an PNG image on a newer iOS device:</p>

<p>SVG<img src="http://m.mediarain.com/images/rain.svg" width="300" height="222" /></p>

<p>PNG<img src="http://m.mediarain.com/images/rain.png" width="300" height="222" /></p>

<h2>Sample Code</h2>

<p>With the full screen mode test above added and a built-in SVG test in-place already, we can add an additional header in our CSS that will only show up if we are in fullscreen (standalone) mode. We can also change the corner image to use an SVG where support is enabled. (Unfortunately, Android does not yet support SVG graphics). We don't check for gradient support, because CSS actually provides a built-in fallback mechanism used below.</p>

<pre class="css">
/****************** 
 * Full Screen Mode
 ******************/
.standalone header > nav {
	background: black;
	background: -moz-linear-gradient(56% 46% 90deg,#000000, #999, #555 0%,#000000 100%);
	background: -webkit-gradient(
		linear, 0% 100%, 0% 0%,
		from(#030303),
		to(#212121),
		color-stop(.5,#000000),
		color-stop(.5,#444),
		color-stop(1,#000000)
	);
	width: 100%;
	height: 54px;
	margin: 0;
	padding: 0;
	clear: both;
	display: block;
}
.standalone header > nav::after {
	content: url('../images/rain.png');
	float: right;
	position: relative;
	top: 24px;
	right: 5%;
}
.svg.standalone header > nav::after {
	content: url('../images/rain.svg');
}
.standalone header > nav > a.back-button {
	background: transparent url('../images/back_both.png') top left;
	display: block;
	padding: 0;
	position: relative;
	top: 13px;
	left: 8px;
	width: 57px;
	height: 30px;
}
.standalone header > nav a.back-button:hover {
	background-position: bottom left;
}
</pre>


<img src="http://www.touchenabledweb.com/img/help_me.png" />

<h2>Conclusion</h2>

<p>That's how you use Modernizr to provide progressive enhancement to your website. We hope you like the work we've done with the mobile web and encourage you to take a look at our mobil app cheat sheet on your phone today!</p>

<h2>Reference</h2>
<ul>
	<li><a href="http://www.modernizr.com">Modernizr.js</a></li>
	<li><a href="http://m.mediarain.com">Rain Mobile App Cheat Sheet</a></li>
</ul>