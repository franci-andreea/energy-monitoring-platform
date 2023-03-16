import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {AiOutlineSend} from "react-icons/ai"
import {BsCheckAll} from "react-icons/bs"
import axios from "axios";
import {ChatServiceClient} from "../grpc/chat_grpc_web_pb" //aici am service
import {Message, User, TypingUser, TypingNotification, TypingStatus, ReadNotification} from "../grpc/chat_pb"; //aici am tipurile de date

const ChatPage = () =>
{
    let navigate = useNavigate();
    
    const [chatService, __] = useState(new ChatServiceClient("http://localhost:9000"));
    const [currentUser, _] = useState(useLocation().state);
    const changeIndex = useRef(0);
    const [change, setChange] = useState(changeIndex);
    const [writingMessage, setWritingMessage] = useState("");
    const writeMessageRef = useRef(writingMessage);
    const [shownName, setShwonName] = useState("");
    const shownNameRef = useRef(shownName);

    const users = useRef([]);
    const shownMessages = useRef([]);

    function update()
    {
        changeIndex.current++;
        setChange(changeIndex.current);
    }

    function updateShownName(newName)
    {
        setShwonName(newName);
        shownNameRef.current = newName;
    }

    function updateWritingMessage(newMessage)
    {
        setWritingMessage(newMessage);
        writeMessageRef.current = newMessage;
    }

    useEffect(
        () => {
            if(checkUser())
            {
                getUsers().then((val) => { users.current = val.map((v) =>  {
                                        return {username:v.username, 
                                                numberOfUnreadMessages:0, 
                                                history:[], 
                                                isTyping:false}
                                    });
                                    update();}
                );
                
            }
        }, []
    )

    function onMessageReceived(message)
    {
        let receivedMessage = {isYou: false, content: message.getContent(), isSeen: false};
        for(let i = 0; i <= users.current.length; ++i)
        {
            if(users.current[i].username === message.getSender())
            {
                if(users.current[i].username !== shownNameRef.current)
                    users.current[i].numberOfUnreadMessages++;
                users.current[i].history.push(receivedMessage);
                break;
            }
        }
        
    }

    function recieveMessage()
    {
        const me = new User();
        me.setUsername(currentUser.username);
        const messasgeStream = chatService.receiveMessage(me, {});
        messasgeStream.on("data", onMessageReceived);

        update();
    }

    function onReadNotificationReceived(user)
    {
        console.log("on read notification received function");
        for(let i = 0; i <= users.current.length; ++i)
        {
            if(users.current[i].username === user.getUsername())
            {
                for(let j = 0; j <= users.current[i].history.length; ++j)
                {
                    users.current[i].history[j].isSeen = true;
                }

                break;
            }
        }
    }

    function receiveReadNotification()
    {
        const me = new User();
        me.setUsername(currentUser.username);
        const readUserStream = chatService.subscribeToMessageHasBeenRead(me, {});
        readUserStream.on("data", onReadNotificationReceived);
    }

    function sendReadNotification()
    {
        if(shownNameRef.current === "")
        {
            return;
        }

        let readNotification = new ReadNotification();
        readNotification.setSender(currentUser.username);
        readNotification.setReceiver(shownNameRef.current);

        chatService.notifyReceiverHasReadMessages(readNotification, {});
    }

    function onIsTypingReceived(typingUser)
    {
        for(let i = 0; i <= users.current.length; ++i)
        {
            if(users.current[i].username === typingUser.getUsername())
            {
                users.current[i].isTyping = (typingUser.getStatus() === TypingStatus.IS_TYPING);
                break;
            }
        }
    }

    function receiveIsTypingNotification()
    {
        const me = new User();
        me.setUsername(currentUser.username);
        const isTypingUsersStream = chatService.subscribeForTypingUser(me, {});
        isTypingUsersStream.on("data", onIsTypingReceived);
    }

    function sendNotifyIsTyping()
    {
        if(shownNameRef.current === "")
        {
            return;
        }

        let typingUser = new TypingUser();
        typingUser.setUsername(currentUser.username);
        typingUser.setStatus(writeMessageRef.current !== "" ? TypingStatus.IS_TYPING : TypingStatus.NOT_TYPING );

        let notificationIsTyping = new TypingNotification();
        notificationIsTyping.setSender(typingUser);
        notificationIsTyping.setReceiver(shownNameRef.current);

        chatService.notifyReceiverIsTyping(notificationIsTyping, {});
    }

    useEffect(() => {
        var interval = setInterval(recieveMessage, 1000);
        var interval1 = setInterval(receiveIsTypingNotification, 1000);
        var interval2 = setInterval(sendNotifyIsTyping, 500);
        var interval3 = setInterval(receiveReadNotification, 1000);
        var interval4 = setInterval(sendReadNotification, 1000);
        return () => {clearInterval(interval); clearInterval(interval1); clearInterval(interval2); clearInterval(interval3); clearInterval(interval4);}
    }, [])

    function checkUser() 
    {
        if (currentUser == null) 
        {
            navigate('/');
            return false;
        } 
        else 
        {
            return true;
        }
    }

    function getUsers()
    {
        return axios.get(process.env.REACT_APP_SERVER_URL + '/get-users', {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(
            response => {
                return response.data;
            }
        )
        .catch(
            function(error)
            {
                console.log("get problems from backend - some error occurred");
                console.log(error);
                return [];
            }
        )
    }

    function sendMessage()
    {
        if(shownNameRef.current === "")
            return;

        let message = {isYou: true, content: writingMessage, isSeen: false};
        shownMessages.current.push(message);
        
        let messageForServer = new Message();
        messageForServer.setSender(currentUser.username);
        messageForServer.setReceiver(shownNameRef.current);
        messageForServer.setContent(writingMessage);
        chatService.sendMessage(messageForServer, {});

        update();
        updateWritingMessage("");
    }

    return (currentUser) ? (
        <div className="font-poppins">
            <div className="flex m-8 space-x-20">
                <div className="flex-none">
                    <button onClick={() => {currentUser.role === 'ADMIN' ? navigate('/admin', { state: currentUser }) : navigate('/user', { state: currentUser })}}>{currentUser.role === 'ADMIN' ? "Admin section" : "User section"} - {currentUser.username}</button>
                </div>
                <div className="flex-grow" />
                <button className="logout-button" onClick={() => { navigate('/'); }}>↦</button>
            </div>

            <hr></hr>

            <div className="grid grid-cols-3">
                <div className="border-r-4 h-screen">
                    <p className="mt-3 ml-3 mb-3 text-lg font-extrabold">Your active chats</p>
                    <hr></hr>
                    {users.current.map(
                        (contact) => 
                            <div onClick={() => {shownMessages.current = contact.history; sendReadNotification(); updateShownName(contact.username); update(); contact.numberOfUnreadMessages = 0; }}>
                                <ListContactItem contact={contact}/>
                            </div>
                        )
                    }
                </div>

                <div className="col-span-2 flex flex-col h-full w-full">
                    <div className="flex flex-col grow">
                        <div className="ml-3 mt-3 mb-3 text-lg font-extrabold">{shownName}</div>
                        <hr></hr>
                        <br></br>
                        {shownMessages.current.map(
                            (message) => 
                                <div className={`${message.isYou ? "flex place-self-end text-slate-100 pr-2 pl-2 bg-purple-400 rounded-md mb-1" : "place-self-start text-purple-700 bg-gray-300 rounded-md mb-1 pr-2 pl-2"}`}>
                                    <div>{message.content}</div>
                                    {message.isYou ? <BsCheckAll className={`${message.isSeen ? "place-self-end fill-yellow-200" : "place-self-end fill-purple-900"}`}/> : <></>}
                                </div>
                            )
                        }
                    </div>

                    <div className="pb-32 pl-2 pr-2 flex flex-row">
                        <input type="text" className="border-2 border-purple-200 rounded-xl w-full pl-3" placeholder="Your Message..." 
                            value={writingMessage} 
                            onChange={e => {updateWritingMessage(e.target.value)}}
                        />
                        <button className="text-purple-500 ml-3 mr-1" onClick={sendMessage}>
                            <AiOutlineSend size="28"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>) : (<h>loading...</h>)
}

const ListContactItem = ({contact}) =>
(
    <div className="hover:bg-purple-100">
        <div className="font-poppins ml-4 mb-4 relative">
            <br></br>
            <p className="font-bold">{contact.username}</p>
            <p className={contact.isTyping || contact.numberOfUnreadMessages !== 0 ? "font-semibold" : ""}>
                {contact.isTyping ? "is typing..." : (contact.history.length !== 0 ? contact.history[contact.history.length-1].content.substr(0, 20) : "No messages sent yet")}
            </p>

            {(contact.numberOfUnreadMessages !== 0) ? (<div className="absolute flex items-center justify-center top-4 right-4 h-6 w-6 text-white border-purple-500 border-2 rounded-full bg-purple-500">
                {contact.numberOfUnreadMessages}
            </div>) : (<></>)}
        </div>
        <hr></hr>
    </div>
);

export default ChatPage;