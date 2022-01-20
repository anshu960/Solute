const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const Dotenv = require('dotenv-webpack');
const webpack = require("webpack");
const environment = require("./../environment");
module.exports = {
  // the output bundle won't be optimized for production but suitable for development
  // mode: 'production',
  mode: environment.mode,
  // the app entry point is /src/index.js
  entry: path.resolve(__dirname, 'src', 'index.js'),
  output: {
  	// the output of the webpack build will be in /dist directory
    path: path.resolve(__dirname, 'public'),
    // the filename of the JS bundle will be bundle.js
    filename: "[name].[hash].js"
  },
  module: {
    rules: [
      {
      	// for any file with a suffix of js or jsx
        test: /\.(js|jsx)(\?\S*)?$/,
        // ignore transpiling JavaScript from node_modules as it should be that state
        exclude: /node_modules/,
        // use the babel-loader for transpiling JavaScript to a suitable format
        loader: 'babel-loader',
        options: {
          // attach the presets to the loader (most projects use .babelrc file instead)
          presets: ["@babel/preset-env", "@babel/preset-react"]
        }
      },
      {
        test: /\.css$/,
        use: [
          {
            loader: MiniCssExtractPlugin.loader,
            options: {
              // you can specify a publicPath here
              // by default it uses publicPath in webpackOptions.output
              publicPath: './public/',
              //hmr: process.env.NODE_ENV === 'development',
            },
          },
          'css-loader',
        ],
      },
      {
        test: /\.(png|svg|jpg|gif)$/,
        use: ['file-loader'],
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: ['file-loader'],
    },
    ]
  },
  resolve: {
    fallback: {
      process: require.resolve("process/browser"),
      zlib: require.resolve("browserify-zlib"),
      stream: require.resolve("stream-browserify"),
      util: require.resolve("util"),
      buffer: require.resolve("buffer"),
      asset: require.resolve("assert"),
    }
  },
  plugins: [new HtmlWebpackPlugin({ hash: true,template: path.resolve(__dirname, 'src', 'index.html') }),
  new MiniCssExtractPlugin({
    // Options similar to the same options in webpackOptions.output
    // all options are optional
    filename: '[name].css',
    chunkFilename: '[id].css',
    //ignoreOrder: false, // Enable to remove warnings about conflicting order
  }),
  new webpack.ProvidePlugin({
    Buffer: ["buffer", "Buffer"],
    process: "process/browser",
  }),
  new Dotenv()
  ]
};