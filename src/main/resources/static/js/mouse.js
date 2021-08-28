/*鼠标跟随*/
function fairyDustCursor() {

    var possibleColors = ["#D61C59", "#E7D84B", "#1B8798"]
    var width = window.innerWidth;
    var height = window.innerHeight;
    var cursor = { x: width / 2, y: width / 2 };
    var particles = [];

    function init() {
    bindEvents();
    loop();
}

    // Bind events that are needed
    function bindEvents() {
    document.addEventListener('mousemove', onMouseMove);
    window.addEventListener('resize', onWindowResize);
}

    function onWindowResize(e) {
    width = window.innerWidth;
    height = window.innerHeight;
}

    function onMouseMove(e) {
    cursor.x = e.clientX;
    cursor.y = e.clientY;

    addParticle(cursor.x, cursor.y, possibleColors[Math.floor(Math.random() * possibleColors.length)]);
}

    function addParticle(x, y, color) {
    var particle = new Particle();
    particle.init(x, y, color);
    particles.push(particle);
}

    function updateParticles() {

    // Updated
    for (var i = 0; i < particles.length; i++) {
    particles[i].update();
}

    // Remove dead particles
    for (var i = particles.length - 1; i >= 0; i--) {
    if (particles[i].lifeSpan < 0) {
    particles[i].die();
    particles.splice(i, 1);
}
}

}

    function loop() {
    requestAnimationFrame(loop);
    updateParticles();
}

    /**
     * Particles
     */

    function Particle() {

    this.character = "*";
    this.lifeSpan = 120; //ms
    this.initialStyles = {
    "position": "fixed",
    "display": "inline-block",
    "top": "0px",
    "left": "0px",
    "pointerEvents": "none",
    "touch-action": "none",
    "z-index": "10000000",
    "fontSize": "50px",
    "will-change": "transform"
};

    // Init, and set properties
    this.init = function (x, y, color) {

    this.velocity = {
    x: (Math.random() < 0.5 ? -1 : 1) * (Math.random() / 2),
    y: 1
};

    this.position = { x: x + 10, y: y + 10 };
    this.initialStyles.color = color;

    this.element = document.createElement('span');
    this.element.innerHTML = this.character;
    applyProperties(this.element, this.initialStyles);
    this.update();

    document.querySelector(".js-cursor-container").append(this.element);

};

    this.update = function () {
    this.position.x += this.velocity.x;
    this.position.y += this.velocity.y;
    this.lifeSpan--;

    this.element.style.transform = "translate3d(" + this.position.x + "px," + this.position.y + "px, 0) scale(" + (this.lifeSpan / 120) + ")";
}

    this.die = function () {
    this.element.parentNode.removeChild(this.element);
}

}

    /**
     * Utils
     */

    // Applies css `properties` to an element.
    function applyProperties(target, properties) {
    for (var key in properties) {
    target.style[key] = properties[key];
}
}

    if (!('ontouchstart' in window || navigator.msMaxTouchPoints)) init();
}

    /*鼠标点击*/
function mouseEnter(){
    var a_idx = 0;
    window.onclick = function(event){
    var a = new Array("*^_^*富强*^_^*","❤民主❤","*^_^*文明*^_^*","❤和谐❤","*^_^*自由*^_^*","❤平等❤","*^_^*公正*^_^*","❤法治❤","*^_^*爱国*^_^*","❤敬业❤","*^_^*诚信*^_^*","❤友善❤");

    var heart = document.createElement("b");	//创建b元素
    heart.onselectstart = new Function('event.returnValue=false');	//防止拖动

    document.body.appendChild(heart).innerHTML = a[a_idx];		//将b元素添加到页面上
    a_idx = (a_idx + 1) % a.length;
    heart.style.cssText = "position: fixed;left:-100%;";	//给p元素设置样式

    var f = 16, 	// 字体大小
    x = event.clientX - f / 2, // 横坐标
    y = event.clientY - f, // 纵坐标
    c = randomColor(),  // 随机颜色
    a = 1, 				// 透明度
    s = 1.2; 			// 放大缩小

    var timer = setInterval(function(){		//添加定时器
    if(a <= 0){
    document.body.removeChild(heart);
    clearInterval(timer);
}else{
    heart.style.cssText = "font-size:16px;cursor: default;position: fixed;color:" + c + ";left:" + x + "px;top:" + y + "px;opacity:" + a + ";transform:scale(" + s + ");";

    y--;
    a -= 0.016;
    s += 0.002;
}
},15)

}
    // 随机颜色
    function randomColor() {

    return "rgb(" + (~~(Math.random() * 255)) + "," + (~~(Math.random() * 255)) + "," + (~~(Math.random() * 255)) + ")";

}
}