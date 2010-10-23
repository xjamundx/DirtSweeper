<article>
	<h1>Mobile web application basics.</h1>
	<nav>
	<ul>
		<li><a href="#introduction">Introduction</a></li>
		<li><a href="#design">Start With Your Design</a></li>
		<li><a href="#structure">Basic Structure</a></li>
		<li><a href="#meta">Icons, full-screen mode, and other options</a></li>
		<li><a href="#html5">HTML5 Building Blocks</a></li>
		<li><a href="#style">Styling the App</a></li>
		<li><a href="#javascript">Javascript Magic</a></li>
		<li><a href="#appcache">An App Cache Makes An App Load Fast</a></li>
		<li><a href="#summary">Summary</a></li>
		<li><a href="#links">Available resources</a></li>
	</ul>
	</nav>
</article>


<article id="introduction">
	
	<h1>Introduction</h1>
	
	<p>Making a mobile web application is fun! This tutorial will guide you through the process by showing you the simple steps needed to create your first mobile web app using a little PHP and the jQuery framework. We'll be basing this tutorial off of the <a href="http://m.mediarain.com">Rain Mobile Cheat Sheet</a>, a little web app I developed for my employer, <a href="http://www.mediarain.com">Rain</a> that describes the current mobile environment.</p>
</article>

<article id="design">
	
	<h1>Start with your Design</h1>
	
	<p>While developers like myself often forget this step it's important to step back and actually design your mobile web application. What do you want it to look like? How do you want the users to navigate around the app? How do we keep the user focused on the most important content? These questions are often best decided with a group of UX specialists, graphics designers, developers and other stake holders involved.</p>
	
	<p>The Rain Mobile Cheat Sheet was designed with marketing and sales people in mind that aren't especially technical. We wanted to keep it really simple and would like to guide them through each of the steps. We also wanted them to be able to call Rain directly from the app. Additionally we wanted it to look good across all iPhones as well as Android phones.</p>
	
	<p>Here are two examples of the design that we came up with for the Rain Mobile Cheat Sheet:</p>
	
	<figure>
		<img src="/img/toc.png" />
		<figcaption>Table of Contents / Start Page.</figcaption>
	</figure>
	
	<figure>
		<img src="/img/help_me.png" />
		<figcaption>An Inside Page With Navigation.</figcaption>
	</figure>
	
	<p>Notice that we have made them no greater than 320px wide. We also used a standard iPhone looking back button and gradient toolbar in addition to the more web looking previous and next buttons.</p>
	
</article>

<article id="structure">
	
	<h1>Basic structure</h1>
	<p>There are a number of schools of thought about how you might want to structure the files in your mobile application. If you're using a framework like jQtouch or SenchaTouch you're likely developing an iPhone-centric web-app and you probably will need only 3 files:</p>
	<ol>
		<li>html file (index.html)</li>
		<li>stylesheet (style.css)</li>
		<li>javascript (main.js)</li>
	</ol>
	<p>We went with a different approach that at least theoretically should support a wider range of mobile phones. We created 1 file for each page and then we have some javascript and a stylesheet:</p>
	<ul>
		<li>index.php</li>
		<li>1.php</li>
		<li>2.php</li>
		<li>3.php</li>
		<li>4.php</li>
		<li>5.php</li>
		<li>6.php</li>
		<li>7.php</li>
		<li>9.php</li>
		<li>style.css</li>
		<li>main.js</li>
	</ul>
	<p>Using this second approach you can guarantee your back button will work on android and other platforms and with a few tricks we can still enable full screen mode on the iPhone.</p>
</article>

<article id="meta">

	<h1>Icons, full-screen mode, and other options</h1>

		There are a number of options you can specify for your iPhone application. Some of the basics are below.

		<ul>
			<li>Home Screen Icon (Android / iPhone)</li>
			<li>Viewport (Android / iPhone)</li>
			<li>Zoom (Android / iPhone)</li>
			<li>Loading Image (iPhone only)</li>
			<li>Fullscreen Mode (iPhone only)</li>
			<li>Status Bar color (iPhone only)</li>
		</ul>
		
		Here is what those options look like in your HTML:
		<pre>
&lt;link rel="apple-touch-icon" href="images/icon.png" /&gt;
&lt;link rel="apple-touch-icon-precomposed" href="images/icon.png" /&gt;
&lt;link rel="apple-touch-startup-image" href="images/start.png" /&gt;
&lt;meta name="apple-mobile-web-app-capable" content="yes" /&gt;
&lt;meta name="apple-mobile-web-app-status-bar-style" content="black" /&gt;
&lt;meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0" /&gt;
		</pre>
		
		Briefly I will touch on some specifics about each of these.
		
		<ol>
			<li>The home screen icon works in both Android and Safari and you probably only need one or the other. Pre-composed means that it will not have the glossy shine applied to it on the iPhone that the iPhone automatically applies to most icons. </li>
			<li>The viewport for a web app should usually be set to the device width and the initial-scale to 1.0. User-scalable we left on as an easy way for users to zoom in to images. Most web apps I imagine set it to no.</li>
			<li>The startup image must be 320px wide by 460px high and only works for iPhone apps. Without one the (rather lengthy) startup process will be complemented with a blank white screen or a screen shot of the last time the app was viewed. This is only used when your app is launched from the home screen.</li>
			<li>Fullscreen mode is what happens when you set apple-mobile-web-app-capable to yes. It only works on the iPhone when you choose to add it to your home screen and launch it from there.</li>
			<li>Status bar color can be black, black-translucent or default.</li>
		</ol>
	
		<p>Here is the icon image I used:</p>
		<img src="http://m.mediarain.com/images/icon.png">

		
		<p>For a full list of options supported by Safari Mobile see Apple's web development documentation:</p>
		<ul>
			<li>
			<a href="http://developer.apple.com/safari/library/documentation/appleapplications/reference/safarihtmlref/articles/metatags.html">http://developer.apple.com/.../metatags.html</a>
			</li>
			<li>
			<a href="http://developer.apple.com/safari/library/documentation/appleapplications/reference/safariwebcontent/configuringwebapplications/configuringwebapplications.html">http://developer.apple.com/.../configuringwebapplications.html</a>
			</li>
		</ul>
		
		
		
</article>

<article id="html5">
	<h1>HTML5 Building Blocks</h1>
	In order to make this whole thing come together we use some pretty fancy technologies in a simple and powerful way. Let's start with the HTML. 
	
	Here is the code for our table of contents:
	
	<pre>
	
&lt;section&gt;
&lt;header&gt;
    &lt;hgroup&gt;
        &lt;h1&gt;The mobile app cheatsheet&lt;/h1&gt;
        &lt;h3&gt;Super cool and handy.&lt;/h3&gt;
    &lt;/hgroup&gt;
&lt;/header&gt;
&lt;article&gt;
&lt;nav class="toc"&gt;
    &lt;ol&gt;
        &lt;a href="1.php" name="1"&gt;
            &lt;li&gt;
                &lt;h1&gt;Why mobile?&lt;/h1&gt;
                &lt;span&gt;Quotes and figures to astound your clients.&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="2.php" name="2"&gt;
            &lt;li&gt;
                &lt;h1&gt;Focus.&lt;/h1&gt;
                &lt;span&gt;Using constraints to improve design across the board.&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="3.php" name="3"&gt;
            &lt;li&gt;
                &lt;h1&gt;Capabilities.&lt;/h1&gt; 
                &lt;span&gt;What can mobile phones do now-a-days?&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="4.php" id="4"&gt;
            &lt;li&gt;
                &lt;h1&gt;Native vs. Web App.&lt;/h1&gt; 
                &lt;span&gt;Which is a better fit for your needs?&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="5.php" id="5"&gt;
            &lt;li&gt;
                &lt;h1&gt;Workflow.&lt;/h1&gt; 
                &lt;span&gt;What happens to your app when it comes through our door.&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="6.php" id="6"&gt;
            &lt;li&gt;
                &lt;h1&gt;Cost.&lt;/h1&gt;
                &lt;span&gt;An arm and a ...&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="7.php" id="7"&gt;
            &lt;li&gt;
                &lt;h1&gt;What's on the horizon?&lt;/h1&gt;
                &lt;span&gt;Mobile trends to keep in mind.&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
        &lt;a href="8.php" id="8"&gt;
            &lt;li&gt;
                &lt;h1&gt;Help me.&lt;/h1&gt;
                &lt;span&gt;We're here to help you with all your Mobile needs.&lt;/span&gt;
            &lt;/li&gt;
        &lt;/a&gt;
    &lt;/ol&gt;
&lt;/nav&gt;
&lt;/article&gt;
&lt;/section&gt;
&lt;footer&gt;
    &lt;a href="contact.php" class="contact-us"&gt;contact us&lt;/a&gt;
    &lt;img src="images/footer.png" class="logo" /&gt;
&lt;/footer&gt;
</pre>
	<p>It should be pretty straight forward with HTML5's emphasis on semantics and everything. Notice that in HTML5 the name attribute of the a tag is no longer needed to create anchors. We simply can link to any id tag in the page, which we chose  leave on the links so that when we're viewing a specific page and we hit our custom back button we can see where we left off. Other things that are neat here are all of the semantic tags like &lt;header&gt;, &lt;hgroup&gt;, &lt;nav&gt;, &lt;footer&gt;, &lt;article&gt; and &lt;section&gt;. &lt;article&gt; has been described as an article of clothing more than a news article, so it's a useful bit of information. I thought on the table of contents page the actual table of contents deserved &lt;article&gt; status. Also, I wrap &lt;a&gt; tags around &lt;li&gt; tags. This does not actually validate but &lt;a&gt; tags in HTML5 are able to contain many sub-elements validly, but apparently not lists. So consider this an easy hack to get the whole list area to be clickable. &lt;a&gt; tags can also link directly to telephone numbers like so:</p>
	
	<pre>
&lt;a href="tel:1-801-802-6464"&gt;Call Rain&lt;/a&gt;</pre>

	<p>Hopefully, that makes some sense. There's nothing really complicated here. The inside pages are all equally simple.</p>
	
	For additional HTML5 resources I recommend checking out the following links:
	<ul>
		<li><a href="http://books.alistapart.com/product/html5-for-web-designers">HTML5 for Web Designers (Book)</a></li>
		<li><a href="http://www.introducinghtml5.com">Introducing HTML5 (Book Site w/Examples)</a></li>
		<li><a href="http://www.diveintohtml5.org">Dive Into HTML5 (Online / Offline Book)</a></li>
		<li><a href="http://html5rocks.com/">HTML 5 Rocks (Tutorials and Examples)</a></li>
	</ul>

</article>	


<article id="style">
	<h1>Styling the App</h1>
	<p>In the comps above you may have noticed a pretty black gradient toolbar with a back button and the rain logo below that and may have assumed this is all images. Well, you would have been half-right. The code is dead simple: </p>	
	<pre>
&lt;nav&gt;
	&lt;a href="index.php#1" class="back-button"&gt;&lt;/a&gt;
&lt;/nav&gt;
	</pre>
	<p>The real magic happens, however, happpens in our style sheet. </p>
	
	<pre>	
header > nav {
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

header > nav::after {
    content: url('../images/rain.svg');
    float: right;
    position: relative;
    top: 24px;
    right: 5%;
}

header > nav > a.back-button {
    background: transparent url('../images/back_both.png') top left;
    display: block;
    padding: 0;
    position: relative;
    top: 13px;
    left: 8px;
    width: 57px;
    height: 30px;
}

header > nav a.back-button:hover {
    background-position: bottom left;
}		
	</pre>

	<p>Lets quickly review what's going on here. You'll notice the &lt;header&gt; tag gets a gradient applied to it and it's width is set to 100%. For Android and Safari we're mainly concerned about the -webkit-gradient setting for background, but the other two are nice fallbacks for other browsers. You'll notice I'm using the ::after CSS psuedo selector to basically create an image tag for me immediately following the header and insert and SVG with the rain logo into it. The SVG file is about the same size as the PNG file (they're both around 1kb), but allows for infinite zooming on all phones and crispness on the iPhone 4 where most images look a little too fuzzy. The :hover CSS pseudo selector is also used to change the PNG background sprite's image position to mimick the iPhone's blue button-selected state.</p>
	
	<p>Here is the button image I used:</p>
	<img src="http://m.mediarain.com/images/back_both.png">
	
	<p>Here is the SVG I used (zoomed 6x):</p>
	<img src="http://m.mediarain.com/images/rain.svg" width="300" height="222">
	
	<p>A PNG alternative I used on Android is here (zoomed 3x):</p>
	<img src="http://m.mediarain.com/images/rain.png" width="300">
	
	<p>Another fancy CSS3 technique we used is on the rounded contact us buttons found on the footer. It's really simple.</p>
	
	<p>The HTML:</p>
	<pre>
&lt;footer&gt;
    &lt;a href="contact.php" class="contact-us"&gt;contact us&lt;/a&gt;
    &lt;img src="images/footer.png" class="logo" /&gt;
&lt;/footer&gt;</pre>
	
	<p>The stylesheet:</p>
	<pre>
footer {
	margin-top: 20px;
	padding: 10px 15px 10px 15px;
	background: rgb(233,242,242);
}

a.contact-us {
	-webkit-border-radius: 9px;
	-moz-border-radius: 9px;
	-moz-border-radius: 9px;
	border-radius: 9px;
	border: 2px solid #00A4E4;
	padding: 7px;
	text-align: center;
	display: block;
	font-size: 2.1em;		
	margin: 15px auto;
}

a.contact-us:hover {
	color: #fff;
	background: #00A4E4;
}


a.contact-us::after {
	content: url('../images/mark.png');
	position: relative;
	top: 3px;
	margin-left: 5px;
}</pre>
	
	<p>Finally, the table of contents uses some really fancy CSS techniques to make for the sexy number formatting. This was not particularly easy to achieve. Separating the numbers from the content of the &lt;li&gt;'s with a "|" instead of a "." and making the font size and colors different took some creative problem solving. Much better though than manually typing the numbers and losing the semantic ease of our tags!</p>
	
	<pre>
ol { counter-reset: item }

li {
	display: block;
	margin-top: 20px;
}

nav.toc > ol li span {
	font-size: 1.6em;
	font-family: 'GothamBook', Arial;
}
nav.toc ol li::before {
	 content: counter(item) " | ";
	 counter-increment: item;
	 font-family: 'GothamLight', Arial;
	 font-size: 2.2em;
}</pre>

	<p>Oh, yeah, the whole site uses custom fonts that don't exist natively on the iPhone. That was surprisingly easy to do (even on the iPhone) using the @font-face tag. We used the Font Squirrel @font-face Generator to generate this code. It works on pretty much all browsers. Android and some other phones have mixed success. Note, the iPhone and iPad only support embedded SVG fonts.</p>
	<p>Resources</p>
	<ul>
		<li><a href="http://www.westciv.com/tools/gradients/">CSS3 Gradient Generator</a></li>
		<li><a href="http://www.fontsquirrel.com/fontface/generator">Font Squirrel @font-face Generator</a></li>
		<li><a href="http://efreedom.com/Question/1-3153019/Remove-decimal-from-ordered-ol-list-via-CSS">Removing . from ordered lists</a></li>
	</ul>
	
</article>

<article id="javascript">
	<h1>Javascript Magic</h1>
	<p>Coming soon...</p>
</article>

<article id="appcache">
	<h1>An App Cache Makes An App Load Fast</h1>
	<p>Coming soon...</p>
</article>

<article id="summary">
	<h1>Summary</h1>
	<p>Coming soon...</p>
</article>

<article id="resources">
	<h1>Available resources</h1>
	<p></p>
	<ul>
		<a href="http://dev.opera.com/articles/view/the-mobile-web-optimization-guide">Opera Mobile Web Optimization Guide</a>	
		<a href="http://www.w3.org/TR/mobile-bp/">W3C Mobile Web Best Practices</a>
		<a href="http://matt.might.net/articles/how-to-native-iphone-ipad-apps-in-javascript/">HOWTO: Native iPhone/iPad apps in JavaScript</a>
		<a href="http://sixrevisions.com/web-development/html5-iphone-app/">Six Revisions: How to make an HTML5 iPhone App</a>
	</ul>
</article>
