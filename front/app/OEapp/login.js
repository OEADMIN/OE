import React, { Component } from 'react';
import {StyleSheet,TextInput, Text, View,Image,TouchableHighlight,Alert } from 'react-native';
import ApiService from './apiservice';

class Login extends Component{
    constructor(props) {
        super(props);
        this.apiService = new ApiService();
        this.state = { 
            code : '',
            pass : ''
        };
    }
    
    handleClickLogin(){
        this.apiService.getData({
            action:"host/signin",
            params:[this.state.code,this.state.pass]
        },function (json) {
            
        })
    }
    render(){
        return(
            <View style={{backgroundColor:'#f4f4f4',flex:1,padding:20}}>  
                <Image  
                    style={styles.style_image}   
                    />  
                <TextInput   
                    style={styles.style_user_input}  
                    placeholder='帐号'  
                    numberOfLines={1}  
                    autoFocus={true}  
                    underlineColorAndroid={'transparent'}   
                    textAlign='left' 
                    onChangeText={(code) => this.setState({code})}
                    value={this.state.code}
                />  
                <View style={{height:1,backgroundColor:'#f4f4f4'}}/>  
                <TextInput   
                    style={styles.style_pwd_input}  
                    placeholder='密码'  
                    numberOfLines={1}  
                    underlineColorAndroid={'transparent'}   
                    secureTextEntry={true}  
                    textAlign='left'  
                    onChangeText={(pass) => this.setState({pass})}
                    value={this.state.pass}
                />  
                <TouchableHighlight   style={styles.style_view_commit} underlayColor='#544b44' onPress ={() => this.handleClickLogin()}>
                    <Text style={{color:'#fff'}} >  
                        登录  
                    </Text>  
                </TouchableHighlight>
            
                <View style={{flex:1,flexDirection:'row',alignItems: 'flex-end',bottom:10}}>  
                    <Text style={styles.style_view_unlogin}>  
                        无法登录?  
                    </Text>  
                    <Text style={styles.style_view_register}>  
                        新用户  
                    </Text>  
                </View>  
            </View>  
        );
    }
}

const styles = StyleSheet.create({  
    style_image:{  
        borderRadius:45,  
        height:70,  
        width:70,  
        marginTop:40,  
        alignSelf:'center',  
    },  
    style_user_input:{ 
        borderRadius:5,     
        backgroundColor:'#fff',  
        marginTop:10,  
        height:45,  
    },  
    style_pwd_input:{   
        borderRadius:5,   
        backgroundColor:'#fff',  
        height:45,  
    },  
    style_view_commit:{    
        marginTop:15,  
        marginLeft:10,  
        marginRight:10,  
        backgroundColor:'#63B8FF',  
        borderColor:'#5bc0de',  
        height:45,  
        borderRadius:5,  
        justifyContent: 'center',  
        alignItems: 'center',  
    },  
    style_view_button:{    
        marginTop:15,  
        marginLeft:10,  
        marginRight:10,  
        backgroundColor:'#63B8FF',  
        borderColor:'#5bc0de',  
        height:45,  
        borderRadius:5,  
        justifyContent: 'center',  
        alignItems: 'center',  
    },  
    style_view_unlogin:{  
        fontSize:15,  
        color:'#63B8FF',  
        marginLeft:10,  
    },  
    style_view_register:{  
        fontSize:15,  
        color:'#63B8FF',  
        marginRight:10,  
        alignItems:'flex-end',  
        flex:1,  
        flexDirection:'row',  
        textAlign:'right',  
    }  
});  
module.exports = Login;
