/**
 * 
 */
package jp.ac.jc21.t.yoshizawa.objectify;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.googlecode.objectify.Ref;

import jp.ac.jc21.t.yoshizawa.CommonFunction;

/**
 * @author t.yoshizawa
 *
 */
public class AnswerSumFactory extends CommonEntity {


	public static final AnswerSum createAnswerSum(String name, Toi Toi, int noOfSeikai,
			Map<String, Ref<Answer>> mapRefAnswer) {
		AnswerSum as = new AnswerSum();
		as.setName(name);
		as.setToiId(Toi.getId());
		as.setAnswered(new Date());
		as.setNoOfSeikai(noOfSeikai);
		as.setNoOfAnswer(mapRefAnswer.size());
		as.setVersion(CommonFunction.ofyVersion);
		return as;
	}

	public static final AnswerSum getById(long id) {
		return (AnswerSum) getById(AnswerSum.class, id);
	}

	public static final Optional<AnswerSum> getOptById(long id) {
		return Optional.ofNullable(getById(id));
	}

	@SuppressWarnings("unchecked")
	public static final List<AnswerSum> loadAll() {
		return (List<AnswerSum>) loadAll(AnswerSum.class);
	}

	public static final List<AnswerSum> loadAll2() {

		List<Member> list = Member.loadAll();

		List<AnswerSum> allList = new ArrayList<AnswerSum>();
		for (Member m : list) {
			List<AnswerSum> asList = AnswerSum.getListByEMail(m.geteMail());
			allList.addAll(asList);
		}
		return allList;
	}



	public final static List<AnswerSum> getListByEMail(String eMail) {

		List<AnswerSum> list = null;
		list = (List<AnswerSum>) loadByIndex(AnswerSum.class, "name", eMail);

		return list;
	}

	public static final List<AnswerSum> getListByToiId(Long toiId) {
		List<AnswerSum> list;
		list = ofy().load().type(AnswerSum.class).filter("toiId", toiId).list();
		list = orderListByMemberId(list);

		return list;
	}

	public static List<AnswerSum> orderListByMemberId(List<AnswerSum> list) {
		list.sort(new Comparator<AnswerSum>() {

			@Override
			public int compare(AnswerSum as1, AnswerSum as2) {
				String memberId1 = as1.getName();
				String memberId2 = as2.getName();
				if (memberId1.equals(memberId2)) {
					return (int) (as1.getAnswered().getTime() - as2.getAnswered().getTime());
				} else {
					return memberId1.compareTo(memberId2);
				}
			}

		});
		return list;
	}

	public static Map<Long, List<AnswerSum>> makeMapByToiId_old(List<AnswerSum> list) {
		Map<Long, List<AnswerSum>> map = new TreeMap<Long, List<AnswerSum>>();
		for (AnswerSum as : list) {
			Long toiId = as.getToiId();
			List<AnswerSum> answerSumList = map.get(toiId);
			if (answerSumList == null) {
				answerSumList = new ArrayList<AnswerSum>();
			}
			answerSumList.add(as);
			map.put(toiId, answerSumList);
		}
		return map;
	}

	public final static Map<Long, List<AnswerSum>> makeMapByToiId(List<AnswerSum> answerSumList) {
		Map<Long, List<AnswerSum>> map2 = answerSumList.stream().filter((AnswerSum aSum) -> {
			return Objects.nonNull(aSum);
		}).collect(Collectors.groupingBy(AnswerSum::getToiId));
		return map2;
	}
}
