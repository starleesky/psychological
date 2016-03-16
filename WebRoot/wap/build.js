({
    appDir: "./",
    baseUrl: "js",
    dir: "../pageMarry",
	optimize: "uglify",
    optimizeCss: "standard", 
    paths: {
    	jquery: 'empty:'
    },
    modules: [
        {
            name: "conf/register"
        },
        {
            name: "conf/index"
        }
    ]
})