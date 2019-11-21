package by.twister.dao;

import by.twister.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:message_query.properties")
public class MessageDaoImpl implements MessageDao {


    @Value("${message.findAll}")
    private String SELECT_ALL_MESSAGE;

    @Value("${message.findById}")
    private String FIND_BY_MESSAGE_ID;

    @Value("${message.add}")
    private String ADD_MESSAGE;

    @Value("${message.upate}")
    private String UPDATE_MESSAGE;

    @Value("${message.delete}")
    private String DELETE_MESSAGE;

    private static final String MESSAGE_ID = "messageId";
    private static final String MESSAGE_CONTENT = "messageContent";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public MessageDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Message> findAll() {
        List<Message> messages = namedParameterJdbcTemplate.query(SELECT_ALL_MESSAGE,
                BeanPropertyRowMapper.newInstance(Message.class));

        return messages;
    }

    @Override
    public Message findById(Integer id) {
        SqlParameterSource parameter = new MapSqlParameterSource(MESSAGE_ID, id);

        Message result = (Message) namedParameterJdbcTemplate.query(FIND_BY_MESSAGE_ID,
                parameter, BeanPropertyRowMapper.newInstance(Message.class));

        return result;
    }

    @Override
    public void add(Message message) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue(MESSAGE_CONTENT, message.getMessageContent());

        KeyHolder generateKey = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(ADD_MESSAGE, parameter, generateKey);

        message.setMessageId(generateKey.getKey().intValue());
    }

    @Override
    public void update(Message message) {
        namedParameterJdbcTemplate.update(UPDATE_MESSAGE,
                new BeanPropertySqlParameterSource(message));
    }

    @Override
    public void delete(Integer id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue(MESSAGE_ID, id);
        namedParameterJdbcTemplate.update(DELETE_MESSAGE, parameter);
    }

}
